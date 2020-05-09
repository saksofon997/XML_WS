import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';

import { OccupancyDialogBoxComponent } from '../occupancy-dialog-box/occupancy-dialog-box.component';
import { DateTime } from "luxon";

export interface UsersData {
  id: number,
  from: DateTime;
  to: DateTime;
  type: string;
}

const ELEMENT_DATA: UsersData[] = [
  { id: 1, from: new Date(1588712400000), to: new Date(1588712400000), type: "Rental" },
  { id: 2, from: new Date(1588712400000), to: new Date(1588712400000), type: "Manual" },
  { id: 3, from: new Date(1588712400000), to: new Date(1588712400000), type: "Rental" },
  { id: 4, from: new Date(1588712400000), to: new Date(1588712400000), type: "Manual" },
];

@Component({
  selector: 'app-occupancy-view',
  templateUrl: './occupancy-view.component.html',
  styleUrls: ['./occupancy-view.component.css']
})
export class OccupancyViewComponent implements OnInit {
  displayedColumns: string[] = ['id', 'from', 'to', 'type', 'action'];
  dataSource = ELEMENT_DATA;

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(OccupancyDialogBoxComponent, {
      width: '420px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result?.event == 'Add') {
        this.addRowData(result.data);
      } else if (result?.event == 'Update') {
        this.updateRowData(result.data);
      } else if (result?.event == 'Delete') {
        this.deleteRowData(result.data);
      }
    });
  }

  addRowData(data) {
    var d = new Date();
    // call backend than push
    this.dataSource.push({
      id: data.id,
      from: data.from,
      to: data.to,
      type: "Manual",
    });
    this.table.renderRows();

  }
  updateRowData(data) {
    // call backend than filter

    this.dataSource = this.dataSource.filter((value, key) => {
      if (value.id == data.id) {
        value.from = data.from;
        value.to = data.to;
      }
      return true;
    });
  }
  deleteRowData(data) {
    // call backend than filter

    this.dataSource = this.dataSource.filter((value, key) => {
      return value.id != data.id;
    });
  }
}
