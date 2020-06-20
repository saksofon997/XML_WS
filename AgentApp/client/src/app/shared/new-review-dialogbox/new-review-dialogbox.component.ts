import { Component, Optional, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Review } from 'src/app/models/Review.model';

@Component({
  selector: 'app-new-review-dialogbox',
  templateUrl: './new-review-dialogbox.component.html',
  styleUrls: ['./new-review-dialogbox.component.css']
})
export class NewReviewDialogboxComponent {

  local_data: Review;

  constructor(
    public dialogRef: MatDialogRef<NewReviewDialogboxComponent>,
    // @Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: Review) {
    this.local_data = { ...data };
  }

  doAction() {
    this.dialogRef.close({ data: this.local_data });
  }

  closeDialog() {
    this.dialogRef.close({ event: 'Cancel' });
  }

}
