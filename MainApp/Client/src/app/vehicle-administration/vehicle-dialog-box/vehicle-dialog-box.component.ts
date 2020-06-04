import { Component, OnInit, Optional, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Car } from 'src/app/models/Car.model';
import { VehicleAdministrationServiceService } from '../vehicle-administration-service.service';


@Component({
  selector: 'app-vehicle-dialog-box',
  templateUrl: './vehicle-dialog-box.component.html',
  styleUrls: ['./vehicle-dialog-box.component.css']
})
export class VehicleDialogBoxComponent{

  action:string;
  local_data: any;
  image_source:any;
  constructor(
    public dialogRef: MatDialogRef<VehicleDialogBoxComponent>,
    public vehicle_service: VehicleAdministrationServiceService,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: Car) {
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
    var vehicle = {
        brand: {
          id: 1,
          name: "BMW"
        },
        model: {
          id: 1,
          name: "X5"
        },
        category: {
          id: 1,
          name: "SUV"
        },
        seats: 5,
        transmission:{
          id: 1,
          name: "6-speed manual"
        },
        fuel: {
          id: 1,
          name: "Gasoline"
        },
        pricelist: null,
        childSeats: 2,
        mileage: 200,
        cdw: 10,
        numberOfStars: 0,
        numberOfReviews: 0,
        locationLongitude: 0,
        locationLatitude: 0
    }

    console.log(this.local_data.images.files);
    this.vehicle_service.addVehicle(vehicle,this.local_data.images.files).subscribe(
      (data: any) => { 
        alert("Vehicle created");
        this.image_source = "http://localhost:8084/vehicle/image/" + data.images[0];
        },
      (error) => { alert(error);  }
    );
    // this.dialogRef.close({event:this.action,data:this.local_data});
  }
 onFileSelected(event) {
    console.log(event.target.files);
    this.local_data.images = event.target.files; 
  }
  closeDialog(){
    this.dialogRef.close({event:'Cancel'});
  }
}
