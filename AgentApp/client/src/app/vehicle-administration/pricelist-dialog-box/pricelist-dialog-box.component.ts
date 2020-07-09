import { Component, Optional, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Pricelist } from 'src/app/models/Pricelist.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-pricelist-dialog-box',
  templateUrl: './pricelist-dialog-box.component.html',
  styleUrls: ['./pricelist-dialog-box.component.css']
})
export class PricelistDialogBoxComponent{

  action:string;
  local_data:any;

  isAgent: boolean;
 
  constructor(
    public dialogRef: MatDialogRef<PricelistDialogBoxComponent>,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: Pricelist,
    
    private userService: UserService) {

    this.local_data = {...data};
    this.action = this.local_data.action;

    this.isAgent = this.userService.getUser().company.equals(null) ? false : true;
  }

  getDisabledValue() {
    if (this.action == "Details")
      return true;
    else
      return false;
  }
 
  doAction(){
    delete this.local_data.action;
    this.dialogRef.close({event:this.action,data:this.local_data});
  }
 
  closeDialog(){
    this.dialogRef.close({event:'Cancel'});
  }
}
