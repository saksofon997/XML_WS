import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from '../dialog-box-edit/dialog-box-edit.component';
import { Category } from 'src/app/models/Category.model';
import { MatPaginator } from '@angular/material/paginator';
import { tap } from 'rxjs/operators';

import { CategoryService } from '../../services/category.service';


@Component({
  selector: 'app-categories-table',
  templateUrl: './categories-table.component.html',
  styleUrls: ['./categories-table.component.css']
})
export class CategoriesTableComponent implements AfterViewInit, OnInit {

  displayedColumns: string[] = ['name', 'action'];
  dataSource: Category[];
  pageNo: number;
  totalPages: number;

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(public dialog: MatDialog,
              private categoryService: CategoryService) {
    this.pageNo = 0;
    this.getCategories(this.pageNo);
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => this.getCategories(this.paginator.pageIndex))
      )
      .subscribe();
  }

  getCategories(pageNo: number) {
    this.categoryService.getPageable(pageNo).subscribe(
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
      if (result.event == 'Add') {
        this.addRowData(result.data);
      } else if (result.event == 'Update') {
        this.updateRowData(result.data);
      } else if (result.event == 'Delete') {
        this.deleteRowData(result.data);
      }
    });
  }

  addRowData(category) {
    this.categoryService.add(category).subscribe(
      (data: Category) => {
        this.getCategories(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }
  updateRowData(category) {
    this.categoryService.edit(category.id, category).subscribe(
      (data: Category) => {
        this.dataSource = this.dataSource.filter((value, key) => {
          if (value.id === category.id) {
            value.name = category.name;
          }
          return true;
        });
      },
      (error) => {
        alert(error);
      }
    );
  }
  deleteRowData(category) {
    this.categoryService.delete(category.id).subscribe(
      (data: Category) => {
        this.getCategories(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }

}
