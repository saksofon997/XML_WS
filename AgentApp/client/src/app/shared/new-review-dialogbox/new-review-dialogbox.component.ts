import { Component, Optional, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Review } from 'src/app/models/Review.model';
import { Car } from 'src/app/models/Car.model';
import { RentalFront } from 'src/app/models/Rental.model';

@Component({
  selector: 'app-new-review-dialogbox',
  templateUrl: './new-review-dialogbox.component.html',
  styleUrls: ['./new-review-dialogbox.component.css']
})
export class NewReviewDialogboxComponent {

  local_data: any;
  rental: RentalFront;
  review: Review;

  constructor(
    public dialogRef: MatDialogRef<NewReviewDialogboxComponent>,
    // @Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any) {
    this.local_data = { ...data };
    this.rental = this.local_data.rental;
    this.review = this.local_data.review;

    console.log(this.rental.car.pricelist.discount);
  }

  doAction() {
    this.dialogRef.close({ data: this.local_data });
  }

  closeDialog() {
    this.dialogRef.close({ event: 'Cancel' });
  }

}
