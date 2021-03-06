import { Component, Inject, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface UsersData {
  name: string;
  id: number;
}

@Component({
  selector: 'app-occupancy-dialog-box',
  templateUrl: './occupancy-dialog-box.component.html',
  styleUrls: ['./occupancy-dialog-box.component.css']
})
export class OccupancyDialogBoxComponent {
  action:string;
  local_data:any;
 
  constructor(
    public dialogRef: MatDialogRef<OccupancyDialogBoxComponent>,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: UsersData) {
    console.log(data);
    this.local_data = {...data};
    this.action = this.local_data.action;
  }
 
  doAction(){
    this.dialogRef.close({event:this.action,data:this.local_data});
  }
 
  closeDialog(){
    this.dialogRef.close({event:'Cancel'});
  }
 
}
