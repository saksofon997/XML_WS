import { Component, OnInit, Input } from '@angular/core';
import { Car } from 'src/app/models/Car.model';
import { environment } from 'src/environments/environment';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from 'src/app/administration/dialog-box-edit/dialog-box-edit.component';
import { OccupancyDialogBoxComponent } from 'src/app/vehicle-page/occupancy-dialog-box/occupancy-dialog-box.component';
import { VehicleOccupancy } from 'src/app/models/VehicleOccupancy.model';
import { OccupancyService } from 'src/app/services/occupancy.service';

@Component({
  selector: 'app-car-teaser-car-info',
  templateUrl: './car-teaser-car-info.component.html',
  styleUrls: ['./car-teaser-car-info.component.css']
})
export class CarTeaserCarInfoComponent implements OnInit {

  @Input() car: Car;
  @Input() admin: boolean;
  API_URL = environment.API_URL;

  constructor(public dialog: MatDialog,
    public occupancyService: OccupancyService) { }

  ngOnInit() {
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(OccupancyDialogBoxComponent, {
      width: '400px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event === 'Update') {

        let occupancy = new VehicleOccupancy(null, Number(result.data.from) / 1000, Number(result.data.to) / 1000, "MANUAL", null);

        this.occupancyService.add(this.car?.id, occupancy).subscribe(
          (data: any) => {
            console.log(data);
          },
          (error) => {
            alert(error);
          }
        );

      } else if (result.event === 'Delete') {
        console.log(result.data); //TODO
      }
    });
  }

  checkMileage(mileage) {
    return mileage != -1 ? mileage : "Unlimited";
  }

}
