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
  {id: 1, from: DateTime.fromMillis(1588712400000).toLocaleString(), to: DateTime.fromMillis(1588712400000).toLocaleString(), type: "Rental" },
  {id: 2, from: DateTime.fromMillis(1588712400000).toLocaleString(), to: DateTime.fromMillis(1588712400000).toLocaleString(), type: "Manual" },
  {id: 3, from: DateTime.fromMillis(1588712400000).toLocaleString(), to: DateTime.fromMillis(1588712400000).toLocaleString(), type: "Rental" },
  {id: 4, from: DateTime.fromMillis(1588712400000).toLocaleString(), to: DateTime.fromMillis(1588712400000).toLocaleString(), type: "Manual" },
];

@Component({
  selector: 'app-occupancy-view',
  templateUrl: './occupancy-view.component.html',
  styleUrls: ['./occupancy-view.component.css']
})
export class OccupancyViewComponent implements OnInit {
  displayedColumns: string[] = ['id', 'from', 'to', 'type'];
  dataSource = ELEMENT_DATA;
 
  @ViewChild(MatTable,{static:true}) table: MatTable<any>;
 
  constructor(public dialog: MatDialog) {}

  ngOnInit(): void {
  }
 
  openDialog(action,obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(OccupancyDialogBoxComponent, {
      width: '300px',
      data:obj
    });
 
    dialogRef.afterClosed().subscribe(result => {
      if(result.event == 'Add'){
        this.addRowData(result.data);
      }else if(result.event == 'Update'){
        this.updateRowData(result.data);
      }else if(result.event == 'Delete'){
        this.deleteRowData(result.data);
      }
    });
  }
 
  addRowData(data){
    var d = new Date();
    this.dataSource.push({
      id: data.id,
      from: data.from,
      to: data.to,
      type: data.type,
    });
    this.table.renderRows();
    
  }
  updateRowData(data){
    this.dataSource = this.dataSource.filter((value,key)=>{
      if(value.id == data.id){
        value.from = data.from;
        value.to = data.to;
        value.type = data.type;
      }
      return true;
    });
  }
  deleteRowData(data){
    this.dataSource = this.dataSource.filter((value,key)=>{
      return value.id != data.id;
    });
  }
}
