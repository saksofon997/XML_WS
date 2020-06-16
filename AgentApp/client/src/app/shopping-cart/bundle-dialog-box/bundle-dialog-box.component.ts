import { Component, Optional, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface BundleData {
  name: string;
  id: number;
}

@Component({
  selector: 'app-bundle-dialog-box',
  templateUrl: './bundle-dialog-box.component.html',
  styleUrls: ['./bundle-dialog-box.component.css']
})
export class BundleDialogBoxComponent {
  action: string;
  local_data: any;
  
  constructor(
    public dialogRef: MatDialogRef<BundleDialogBoxComponent>,
    // @Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: BundleData) {
    this.local_data = {...data};
    this.action = this.local_data.action;
  }
 
  doAction(){
    this.dialogRef.close({event: this.action, data: this.local_data});
  }
 
  closeDialog(){
    this.dialogRef.close({event: 'Cancel'});
  }
}