import { Component, OnInit, ViewChild } from '@angular/core';
import { Car } from '../models/Car.model';
import { Review } from '../models/Review.model';
import { Rental } from '../models/Rental.model';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { BundleDialogBoxComponent } from './bundle-dialog-box/bundle-dialog-box.component';

class Bundle {
  name: string;
  rentals?: Array<Rental>;
  owner: Number;
}

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  bundles = new Array<any>();
  rentals = new Array<Rental>();

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;

  constructor(public dialog: MatDialog) {
    //var review = new Review(4, null, { id: null, name: "" },
    //  { id: null, name: "" },
    //  "");
    //this.rentals.push(new Rental(1, new Car(["https://article.images.consumerreports.org/f_auto/prod/content/dam/CRO%20Images%202018/Cars/November/CR-Cars-InlineHero-2019-Honda-Insight-driving-trees-11-18"],
    //  "Jaguar", "I dont know", "Diesel", "Automatic", "A", 2, "Unlimited", 1000, 2, review, 1), new Date(1588712400000), new Date(1588782400000)));
    //this.rentals.push(new Rental(2, new Car(["https://www.testoviautomobila.rs/wp-content/uploads/2015/05/fica-prelepa-slika-840x420.jpg"],
    //  "Zastava", "500", "Gasoline", "Manual", "A", 15, "Unlimited", 5000, 5, review, 2), new Date(1588712400000), new Date(1588782400000)));

    // var bundle = new Bundle();
    // bundle.name = "First bundle";
    // bundle.rentals = new Array<Rental>();
    // bundle.rentals.push(this.rentals[0]);
    // this.bundles.push(bundle);
    // console.log(this.bundles);

    // var bundle = new Bundle();
    // bundle.name = "Second bundle";
    // bundle.rentals = new Array<Rental>();
    // bundle.rentals.push(this.rentals[1]);
    // this.bundles.push(bundle);
    // console.log(this.bundles);
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
    let bundle = new Bundle();
    bundle.name = data.name;
    bundle.rentals = new Array<Rental>();
    this.bundles.push(bundle);
  }

  deleteBundle(data) {
    var result = this.bundles.find(b => {
      return b.name === data.name;
    });

    for (var rental of result.rentals){
      rental.bundle = null;
    }

    this.bundles = this.bundles.filter((value, key) => {
      return value.name !== data.name;
    });
  }

  removeFromBundle(bundle, rental) {
    bundle.rentals = bundle.rentals.filter((value, key) => {
      return value.id !== rental.id;
    });

    if (bundle.rentals.length === 0){
      bundle.owner = null;
    }
    rental.bundle = null;
  }

}
