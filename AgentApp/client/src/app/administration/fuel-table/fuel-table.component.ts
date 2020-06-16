import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from '../dialog-box-edit/dialog-box-edit.component';
import { Fuel } from 'src/app/models/Fuel.model';
import { MatPaginator } from '@angular/material/paginator';
import { FuelService } from 'src/app/services/fuel.service';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-fuel-table',
  templateUrl: './fuel-table.component.html',
  styleUrls: ['./fuel-table.component.css']
})
export class FuelTableComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['name', 'action'];
  dataSource: Fuel[];
  pageNo: number;
  totalPages: number;

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(public dialog: MatDialog,
    private fuelService: FuelService) {
    this.pageNo = 0;
    this.getFuels(this.pageNo);
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => this.getFuels(this.paginator.pageIndex))
      )
      .subscribe();
  }

  getFuels(pageNo: number) {
    this.fuelService.getPageable(pageNo).subscribe(
      (data: any) => {
        this.dataSource = data.content;
        this.pageNo = data.pageNo;
        this.totalPages = data.totalPages;
      },
      (error) => {
        alert(error);
      }
    );
    // this.fuelService.getAll().subscribe(
    //   (data: any) => {
    //     this.dataSource = data;
    //   },
    //   (error) => {
    //     alert(error);
    //   }
    // )
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxComponent, {
      width: '300px',
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

  addRowData(fuel) {
    this.fuelService.add(fuel).subscribe(
      (data: Fuel) => {
        this.getFuels(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }
  updateRowData(fuel) {
    this.fuelService.edit(fuel.id, fuel).subscribe(
      (data: Fuel) => {
        this.dataSource = this.dataSource.filter((value, key) => {
          if (value.id === fuel.id) {
            value.name = fuel.name;
          }
          return true;
        });
      },
      (error) => {
        alert(error);
      }
    );
  }
  deleteRowData(fuel) {
    this.fuelService.delete(fuel.id).subscribe(
      (data: Fuel) => {
        this.getFuels(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }
}
