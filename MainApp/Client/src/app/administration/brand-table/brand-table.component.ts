import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from '../dialog-box-edit/dialog-box-edit.component';

export interface UsersData {
  name: string;
  id: number;
}
 
const ELEMENT_DATA: UsersData[] = [
  {id: 1560608769632, name: 'Mercedes'},
  {id: 1560608796014, name: 'BMW'},
  {id: 1560608787815, name: 'Nissan'},
  {id: 1560608805101, name: 'Toyota'}
];

@Component({
  selector: 'admin-brand-table',
  templateUrl: './brand-table.component.html',
  styleUrls: ['./brand-table.component.css']
})
export class BrandTableComponent implements OnInit {

  displayedColumns: string[] = ['name', 'action'];
  dataSource = ELEMENT_DATA;
 
  @ViewChild(MatTable,{static:true}) table: MatTable<any>;
 
  constructor(public dialog: MatDialog) {}

  ngOnInit(): void {
  }
 
  openDialog(action,obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxComponent, {
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
 
  addRowData(row_obj){
    var d = new Date();
    this.dataSource.push({
      id:d.getTime(),
      name:row_obj.name
    });
    this.table.renderRows();
    
  }
  updateRowData(row_obj){
    this.dataSource = this.dataSource.filter((value,key)=>{
      if(value.id == row_obj.id){
        value.name = row_obj.name;
      }
      return true;
    });
  }
  deleteRowData(row_obj){
    this.dataSource = this.dataSource.filter((value,key)=>{
      return value.id != row_obj.id;
    });
  }
}
