import { VehicleService } from './../../services/vehicle.service';
import { Router } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Car } from 'src/app/models/Car.model';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { VehicleDialogBoxComponent } from '../vehicle-dialog-box/vehicle-dialog-box.component';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-vehicle-listing',
  templateUrl: './vehicle-listing.component.html',
  styleUrls: ['./vehicle-listing.component.css']
})
export class VehicleListingComponent implements OnInit {
  dataSource: Car[];

  constructor(public dialog: MatDialog,
    private router: Router,
    private vehicleService: VehicleService,
    private userService: UserService) { }

  ngOnInit(): void {
    let userId = this.userService.getUser().id;
    this.vehicleService.getByOwner(userId).subscribe(
      (data: any) => {
        this.dataSource = data;
      },
      (error) => {
        alert(error);
      });
  }

  addVehicle() {
    this.router.navigate(["/vehicle/new", { id: 1 }]);
  }
}
