import { Component, OnInit, ViewChild } from '@angular/core';
import { Review } from 'src/app/models/Review.model';
import { MatTable } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { CategoryService } from 'src/app/services/category.service';
import { tap } from 'rxjs/operators';
import { DialogBoxComponent } from '../dialog-box-edit/dialog-box-edit.component';
import { Category } from 'src/app/models/Category.model';
import { ReviewService } from 'src/app/services/review.service';
import { DateTime } from "luxon";

@Component({
  selector: 'app-pending-reviews',
  templateUrl: './pending-reviews.component.html',
  styleUrls: ['./pending-reviews.component.css']
})
export class PendingReviewsComponent implements OnInit {

  displayedColumns: string[] = ['customerId', 'customerName', 'vehicle', 'stars', 'text', 'date', 'action'];
  dataSource: Review[];
  pageNo: number;
  totalPages: number;

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(public dialog: MatDialog,
    private reviewService: ReviewService) {
    this.pageNo = 0;
    this.getPageablePending(this.pageNo);
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => this.getPageablePending(this.paginator.pageIndex))
      )
      .subscribe();
  }

  getPageablePending(pageNo: number) {
    this.reviewService.getPageablePending(pageNo).subscribe(
      (data: any) => {
        this.dataSource = data.content;
        // for (var i = 0; this.dataSource.length; i++) {
        //   let review = this.dataSource[0]
        //   review.date = DateTime.fromMillis(review.date).toLocaleString();
        // }
        this.pageNo = data.pageNo;
        this.totalPages = data.totalPages;
      },
      (error) => {
        alert(error);
      }
    );
  }

  updateRowData(review) {
    this.reviewService.edit(review.vehicle.id, review.id, review).subscribe(
      (data: Review) => {
        this.getPageablePending(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }
  deleteRowData(review) {
    this.reviewService.delete(review.vehicle.id, review.id).subscribe(
      (data: Review) => {
        this.getPageablePending(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }

}
