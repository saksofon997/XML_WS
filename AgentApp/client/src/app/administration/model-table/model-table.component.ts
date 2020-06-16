import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from '../dialog-box-edit/dialog-box-edit.component';
import { Model } from 'src/app/models/Model.model';
import { ModelService } from 'src/app/services/model.service';
import { MatPaginator } from '@angular/material/paginator';
import { tap } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-model-table',
  templateUrl: './model-table.component.html',
  styleUrls: ['./model-table.component.css']
})
export class ModelTableComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['name', 'action'];
  dataSource: Model[];
  pageNo: number;
  totalPages: number;

  brandId: number;

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(public dialog: MatDialog,
    private modelService: ModelService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {
    this.pageNo = 0;
    this.getModels(this.pageNo);
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => this.getModels(this.paginator.pageIndex))
      )
      .subscribe();
  }

  getModels(pageNo: number) {
    this.activatedRoute.params.subscribe(params => {
      this.brandId = +params['brandId'];
    });

    // this.modelService.getAll(this.brandId).subscribe(
    //   (data: any) => {
    //     this.dataSource = data;
    //   },
    //   (error) => {
    //     alert(error);
    //   }
    // )

    this.modelService.getPageable(this.brandId, pageNo).subscribe(
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

  addRowData(model) {
    this.modelService.add(this.brandId, model).subscribe(
      (data: Model) => {
        this.getModels(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }
  updateRowData(model) {
    this.modelService.edit(this.brandId, model.id, model).subscribe(
      (data: Model) => {
        this.dataSource = this.dataSource.filter((value, key) => {
          if (value.id === model.id) {
            value.name = model.name;
          }
          return true;
        });
      },
      (error) => {
        alert(error);
      }
    );
  }
  deleteRowData(model) {
    this.modelService.delete(this.brandId, model.id).subscribe(
      (data: Model) => {
        this.getModels(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }
}
