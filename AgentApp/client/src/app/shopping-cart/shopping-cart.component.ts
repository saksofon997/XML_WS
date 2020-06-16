import { Component, OnInit, ViewChild } from '@angular/core';
import { RentalBack, RentalFront } from '../models/Rental.model';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { BundleDialogBoxComponent } from './bundle-dialog-box/bundle-dialog-box.component';
import { CookieService } from 'ngx-cookie-service';
import { Bundle } from '../models/Bundle.model';
import { ShoppingCart } from '../models/ShoppingCart.model';
import { RentalService } from '../services/rental.service';

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
    private cookieService: CookieService,
    private rentalService: RentalService) {

    if (this.cookieService.get('shopping-cart')) {
      let cart = JSON.parse(this.cookieService.get('shopping-cart'));
      this.rentals = cart.rentals;
      this.bundles = cart.bundles;
    } else {
      let cart = new ShoppingCart(this.rentals, this.bundles);
      this.cookieService.set('shopping-cart', JSON.stringify(cart));
    }

    //this.cookieService.delete('shopping-cart');
  }

  ngOnInit() {
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(BundleDialogBoxComponent, {
      width: '300px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event === 'Create') {
        this.createBundle(result.data);
      } else if (result.event === 'Delete') {
        this.deleteBundle(result.data);
      }
    });
  }

  createBundle(data) {
    if (this.cookieService.get('shopping-cart')) {
      let cart = JSON.parse(this.cookieService.get('shopping-cart'));

      if (!cart.bundles.some(e => e.name === data.name)) {
        let bundle = new Bundle(data.name, new Array<RentalFront>(), null);
        cart.bundles.push(bundle);
        this.bundles.push(bundle);
      }
      this.cookieService.set('shopping-cart', JSON.stringify(cart));
    } else {
      let cart = new ShoppingCart(new Array(), new Array());

      let bundle = new Bundle(data.name, new Array<RentalFront>(), null);

      cart.bundles.push(bundle);
      this.bundles.push(bundle);

      this.cookieService.set('shopping-cart', JSON.stringify(cart));
    }
  }

  deleteBundle(data) {
    var result = this.bundles.find(b => {
      return b.name === data.name;
    });

    for (var rental of result.rentals) {
      rental.bundle = null;
    }

    this.bundles = this.bundles.filter((value) => {
      return value.name !== data.name;
    });
    let cart = JSON.parse(this.cookieService.get('shopping-cart'));
    cart.bundles = this.bundles;
    this.cookieService.set('shopping-cart', JSON.stringify(cart));

  }

  removeFromBundle(bundle, rental) {
    bundle.rentals = bundle.rentals.filter((value) => {
      return value.id !== rental.id;
    });

    if (bundle.rentals.length === 0) {
      bundle.owner = null;
    }
    rental.bundle = null;
  }

  checkout() {
    let rentals = new Array<RentalBack>();

    var i;
    for (i = 0; i < this.rentals.length; i++) {
      let rentalFront = this.rentals[i];
      let rental = new RentalBack(null, rentalFront.car.id, 1, 1, rentalFront.from, rentalFront.to, null);

      // TODO: user service - customerId
      // TODO: rentalFront.car.ownerId - ownerId
      let bundle = new Bundle(rentalFront.bundle?.name, null, null);

      if (rentalFront.bundle) {
        rental.bundle = bundle;
      }

      rentals.push(rental);
    }

    this.rentalService.checkout(rentals).subscribe(
      () => {
        alert("Checkout successfull");
        this.bundles = new Array();
        this.rentals = new Array();
        this.cookieService.delete('shopping-cart');
      },
      (error) => {
        alert(error);
      }
    );

  }

}
