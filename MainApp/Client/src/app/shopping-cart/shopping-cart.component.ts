import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { RentalBack, RentalFront } from '../models/Rental.model';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { BundleDialogBoxComponent } from './bundle-dialog-box/bundle-dialog-box.component';
import { CookieService } from 'ngx-cookie-service';
import { Bundle } from '../models/Bundle.model';
import { ShoppingCart } from '../models/ShoppingCart.model';
import { RentalService } from '../services/rental.service';
import { UserService } from '../services/user.service';
import { ShoppingCartService } from '../services/shopping-cart.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  bundles = new Array<Bundle>();
  rentals = new Array<RentalFront>();

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;

  constructor(public dialog: MatDialog,
    private shoppingCartService: ShoppingCartService,
    private userService: UserService,
    private rentalService: RentalService) {
  }

  ngOnInit() {
    this.rentals = this.shoppingCartService.getRentals();
    this.bundles = this.shoppingCartService.getBundles();
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(BundleDialogBoxComponent, {
      width: '300px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (!result) {
        return;
      }
      if (result.event === 'Create') {
        this.createBundle(result.data);
      } else if (result.event === 'Delete') {
        this.deleteBundle(result.data);
      }
    });
  }

  createBundle(data) {
    this.shoppingCartService.addBundleToCart(data);
    this.cartUpdated('create');
  }

  deleteBundle(data) {
    this.shoppingCartService.removeBundleFromCart(data);
    this.cartUpdated('delete');
  }

  removeFromBundle(bundle, rental) {
    this.shoppingCartService.removeFromBundle(bundle, rental);
    this.cartUpdated('remove');
  }

  checkout() {
    let rentals = new Array<RentalBack>();

    if (this.rentals.length === 0) {
      return;
    }

    var i;
    for (i = 0; i < this.rentals.length; i++) {
      let rentalFront = this.rentals[i];

      let rental = new RentalBack(rentalFront.id, rentalFront.car.id, null,
        rentalFront.ownerId, rentalFront.from, rentalFront.to, null, null, null);

      rental.customerId = this.userService.getUser().id;

      if (rentalFront.bundle) {
        let bundle = new Bundle(rentalFront.bundle, null, null);

        rental.bundle = bundle;
      }

      rentals.push(rental);
    }

    this.rentalService.checkout(rentals).subscribe(
      () => {
        alert("Checkout successfull");
        //this.bundles = new Array();
        //this.rentals = new Array();
        //this.cookieService.delete('shopping-cart');
        this.shoppingCartService.clearCart();
        this.cartUpdated('delete');
      },
      (error) => {
        alert(error);
      }
    );

  }

  deleteRental($event) {
    this.rentals = this.rentals.filter(x => !(x.car.id === $event.car.id && x.to === $event.to && x.from === $event.from));
  }

  cartUpdated($event) {
    this.bundles = this.shoppingCartService.getBundles();
    this.rentals = this.shoppingCartService.getRentals();
  }
}
