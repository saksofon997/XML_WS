import { Component, Optional, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface Pricing {
  id: number;
  name: string;
  pricePerDay: number;
  cdw: number;
  pricePerKm: number;
  description: string;
}

@Component({
  selector: 'app-pricelist-dialog-box',
  templateUrl: './pricelist-dialog-box.component.html',
  styleUrls: ['./pricelist-dialog-box.component.css']
})
export class PricelistDialogBoxComponent{

  action:string;
  local_data:any;
 
  constructor(
    public dialogRef: MatDialogRef<PricelistDialogBoxComponent>,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: Pricing) {
    this.local_data = {...data};
    this.action = this.local_data.action;
  }

  getDisabledValue() {
    if (this.action == "Details")
      return true;
    else
      return false;
  }
 
  doAction(){
    this.dialogRef.close({event:this.action,data:this.local_data});
  }
 
  closeDialog(){
    this.dialogRef.close({event:'Cancel'});
  }
}
