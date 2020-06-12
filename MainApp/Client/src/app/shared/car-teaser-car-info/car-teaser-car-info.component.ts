import { Component, OnInit, Input } from '@angular/core';
import { Car } from 'src/app/models/Car.model';
import { environment } from 'src/environments/environment';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from 'src/app/administration/dialog-box-edit/dialog-box-edit.component';

@Component({
  selector: 'app-car-teaser-car-info',
  templateUrl: './car-teaser-car-info.component.html',
  styleUrls: ['./car-teaser-car-info.component.css']
})
export class CarTeaserCarInfoComponent implements OnInit {

  @Input() car: Car;
  @Input() admin: boolean;
  API_URL = environment.API_URL;

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxComponent, {
      width: '300px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event === 'Update') {
        console.log(result.data); //TODO for manual occupancy
      } else if (result.event === 'Delete') {
        console.log(result.data); //TODO
      }
    });
  }

  checkMileage(mileage){
    return mileage != -1 ? mileage : "Unlimited";
  }

}
