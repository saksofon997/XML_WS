import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from '../dialog-box-edit/dialog-box-edit.component';
import { BrandService } from '../../services/brand.service';
import { Brand } from 'src/app/models/Brand.model';
import { MatPaginator } from '@angular/material/paginator';
import { tap } from 'rxjs/operators';


@Component({
  selector: 'admin-brand-table',
  templateUrl: './brand-table.component.html',
  styleUrls: ['./brand-table.component.css']
})
export class BrandTableComponent implements AfterViewInit, OnInit {

  displayedColumns: string[] = ['name', 'action'];
  dataSource: Brand[];
  pageNo: number;
  totalPages: number;

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(public dialog: MatDialog,
              private brandService: BrandService) {
    this.pageNo = 0;
    this.getBrands(this.pageNo);
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => this.getBrands(this.paginator.pageIndex))
      )
      .subscribe();
  }

  getBrands(pageNo: number) {
    this.brandService.getPageable(pageNo).subscribe(
      (data: any) => {
        this.dataSource = data.content;
        this.pageNo = data.pageNo;
        this.totalPages = data.totalPages;
      },
      (error) => {
        alert(error);
      }
    );
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxComponent, {
      width: '300px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event === 'Add') {
        this.addRowData(result.data);
      } else if (result.event === 'Update') {
        this.updateRowData(result.data);
      } else if (result.event === 'Delete') {
        this.deleteRowData(result.data);
      }
    });
  }

  addRowData(brand) {
    this.brandService.add(brand).subscribe(
      (data: Brand) => {
        this.getBrands(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }
  updateRowData(brand) {
    this.brandService.edit(brand.id, brand).subscribe(
      (data: Brand) => {
        this.dataSource = this.dataSource.filter((value, key) => {
          if (value.id === brand.id) {
            value.name = brand.name;
          }
          return true;
        });
      },
      (error) => {
        alert(error);
      }
    );
  }
  deleteRowData(brand) {
    this.brandService.delete(brand.id).subscribe(
      (data: Brand) => {
        this.getBrands(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }
}
