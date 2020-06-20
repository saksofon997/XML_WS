import { VehicleService } from './../../services/vehicle.service';
import { Router } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Car } from 'src/app/models/Car.model';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { VehicleDialogBoxComponent } from '../vehicle-dialog-box/vehicle-dialog-box.component';

@Component({
  selector: 'app-vehicle-listing',
  templateUrl: './vehicle-listing.component.html',
  styleUrls: ['./vehicle-listing.component.css']
})
export class VehicleListingComponent implements OnInit {
  dataSource: Car[];

  constructor(public dialog: MatDialog,
    private router: Router,
    private vehicleService: VehicleService) { }

  ngOnInit(): void {
    // To do: set current user ID :D 
    this.vehicleService.getByOwner(1).subscribe(
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
