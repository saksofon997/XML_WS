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
  displayedColumns: string[] = ['brand', 'model', 'category', 'seats', 'numberOfStars'];
  dataSource: Car[]= [];
  imageSource: any;
  @ViewChild(MatTable, { static: true }) table: MatTable<any>;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
    
  }
  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(VehicleDialogBoxComponent, {
      width: '400px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event == 'Add') {
        this.addRowData(result.data);
      } else if (result.event == 'Update') {
        this.updateRowData(result.data);
      } else if (result.event == 'Delete') {
        this.deleteRowData(result.data);
      }
    });
  }
  addRowData(row_obj) {
    var d = new Date();
    // this.dataSource.push({
    //   brand: row_obj.brand,
    //   model: row_obj.model,
    //   category: row_obj.category,
    //   seats: row_obj.seats,
    //   numberOfStars: row_obj.numberOfStars,

    // });
    this.table.renderRows();

  }
  updateRowData(row_obj) {
    // this.dataSource = this.dataSource.filter((value, key) => {
    //   if (value.id == row_obj.id) {
    //     value.name = row_obj.name;
    //     value.pricePerDay = row_obj.pricePerDay;
    //     value.cdw = row_obj.cdw;
    //     value.pricePerKm = row_obj.pricePerKm;
    //     value.description = row_obj.description;
    //   }
    //   return true;
    // });
  }
  deleteRowData(row_obj) {
    // this.dataSource = this.dataSource.filter((value, key) => {
    //   return value.id != row_obj.id;
    // });
  }
}
