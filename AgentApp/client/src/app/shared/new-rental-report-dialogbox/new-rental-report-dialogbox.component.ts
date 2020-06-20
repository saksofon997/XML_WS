import { Component, Optional, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RentalReport } from 'src/app/models/RentalReport.model';

@Component({
  selector: 'app-new-rental-report-dialogbox',
  templateUrl: './new-rental-report-dialogbox.component.html',
  styleUrls: ['./new-rental-report-dialogbox.component.css']
})
export class NewRentalReportDialogboxComponent {

  local_data: RentalReport;

  constructor(
    public dialogRef: MatDialogRef<NewRentalReportDialogboxComponent>,
    // @Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: RentalReport) {
    this.local_data = { ...data };
  }

  doAction() {
    this.dialogRef.close({ data: this.local_data });
  }

  closeDialog() {
    this.dialogRef.close({ event: 'Cancel' });
  }

}

