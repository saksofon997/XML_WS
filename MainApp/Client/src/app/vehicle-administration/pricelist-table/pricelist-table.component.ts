import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { PricelistDialogBoxComponent } from '../pricelist-dialog-box/pricelist-dialog-box.component';
import { Pricelist } from 'src/app/models/Pricelist.model';

const ELEMENT_DATA: Pricelist[] = [
  { id: 1560608769632, ownerId: 1, name: 'Basic Plan', pricePerDay: 50, cdw: 5000, pricePerKm: 5, description: 'Custom description here' },
  { id: 1560608796014, ownerId: 1, name: 'Premium Plan', pricePerDay: 70, cdw: 7700, pricePerKm: 7, description: 'Custom description here' },
  { id: 1560608787815, ownerId: 1, name: 'Family Plan', pricePerDay: 40, cdw: 6000, pricePerKm: 6, description: 'Custom description here' },
  { id: 1560608805101, ownerId: 1, name: 'Adventure Plan', pricePerDay: 30, cdw: 15000, pricePerKm: 10, description: 'Custom description here' }
];

@Component({
  selector: 'app-pricelist-table',
  templateUrl: './pricelist-table.component.html',
  styleUrls: ['./pricelist-table.component.css']
})
export class PricelistTableComponent implements OnInit {
  displayedColumns: string[] = ['name', 'pricePerDay', 'cdw', 'pricePerKm', 'action'];
  dataSource = ELEMENT_DATA;

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(PricelistDialogBoxComponent, {
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
    this.dataSource.push({
      id: d.getTime(),
      ownerId: row_obj.ownerId,
      name: row_obj.name,
      pricePerDay: row_obj.pricePerDay,
      cdw: row_obj.cdw,
      pricePerKm: row_obj.pricePerKm,
      description: row_obj.description
    });
    this.table.renderRows();

  }
  updateRowData(row_obj) {
    this.dataSource = this.dataSource.filter((value, key) => {
      if (value.id == row_obj.id) {
        value.name = row_obj.name;
        value.pricePerDay = row_obj.pricePerDay;
        value.cdw = row_obj.cdw;
        value.pricePerKm = row_obj.pricePerKm;
        value.description = row_obj.description;
      }
      return true;
    });
  }
  deleteRowData(row_obj) {
    this.dataSource = this.dataSource.filter((value, key) => {
      return value.id != row_obj.id;
    });
  }
}
