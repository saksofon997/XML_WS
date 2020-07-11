import { Component, Optional, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RentalReport } from 'src/app/models/RentalReport.model';
import { RentalFront } from 'src/app/models/Rental.model';

@Component({
  selector: 'app-new-rental-report-dialogbox',
  templateUrl: './new-rental-report-dialogbox.component.html',
  styleUrls: ['./new-rental-report-dialogbox.component.css']
})
export class NewRentalReportDialogboxComponent {

  local_data: any;
  report: RentalReport;
  rental: RentalFront;

  penalty: boolean = false;
  mileage: any = 0;
  penaltyValue: any;

  constructor(
    public dialogRef: MatDialogRef<NewRentalReportDialogboxComponent>,
    // @Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any) {
    this.local_data = { ...data };

    this.report = this.local_data.report;
    this.rental = this.local_data.rental;
  }

  calculatePenalty(event) {
    this.mileage = event.target.value;
    
    if((this.mileage > this.rental.car.availableMileage) && (this.rental.car.availableMileage != -1) && (this.rental.car.pricelist.penalty != 0)) {
      this.penalty = true;
      this.penaltyValue = (this.mileage - this.rental.car.availableMileage) * this.rental.car.pricelist.penalty;
    }
    else {
      this.penalty = false;
      this.penaltyValue = null;
    }
  }

  getPenalty() {
    return this.penaltyValue;
  }

  finalPrice() {
    let duration = this.rental.to - this.rental.from;

    let days = Math.ceil(duration/(60*60*24));

    let price =  days * this.rental.car.pricelist.pricePerDay;

    if(days > 10) {
      price = price -  price * this.rental.car.pricelist.discount;
    }

    return price;
  }

  doAction() {
    this.dialogRef.close({ data: this.local_data });
  }

  closeDialog() {
    this.dialogRef.close({ event: 'Cancel' });
  }

}

