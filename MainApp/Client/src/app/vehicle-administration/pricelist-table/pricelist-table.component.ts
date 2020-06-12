import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { PricelistDialogBoxComponent } from '../pricelist-dialog-box/pricelist-dialog-box.component';
import { Pricelist } from 'src/app/models/Pricelist.model';
import { PricelistService } from 'src/app/services/pricelist.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pricelist-table',
  templateUrl: './pricelist-table.component.html',
  styleUrls: ['./pricelist-table.component.css']
})
export class PricelistTableComponent implements OnInit {
  displayedColumns: string[] = ['name', 'pricePerDay', 'cdw', 'pricePerKm', 'action'];
  dataSource: Pricelist[];

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;

  constructor(public dialog: MatDialog,
    private pricelistService: PricelistService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.pricelistService.getByOwner(1).subscribe(
      (data: any) => {
        this.dataSource = data;
      },
      (error) => {
        alert(error);
      });
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
