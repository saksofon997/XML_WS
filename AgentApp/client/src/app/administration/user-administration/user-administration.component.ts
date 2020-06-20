import { Component, OnInit, ViewChild } from '@angular/core';
import { User } from 'src/app/models/User.model';
import { MatTable } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { ReviewService } from 'src/app/services/review.service';
import { tap } from 'rxjs/operators';
import { Review } from 'src/app/models/Review.model';
import { UserService } from 'src/app/services/user.service';
import { UserRolesComponent } from './user-roles/user-roles.component';

@Component({
  selector: 'app-user-administration',
  templateUrl: './user-administration.component.html',
  styleUrls: ['./user-administration.component.css']
})
export class UserAdministrationComponent implements OnInit {

  displayedColumns: string[] = ['userId', 'email', 'name', 'surname', 'address', 'phoneNumber', 'role', 'enabled', 'action'];
  dataSource: User[];
  pageNo: number;
  totalPages: number;

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(public dialog: MatDialog,
    private userService: UserService) {
    this.pageNo = 0;
    this.getPageableUsers(this.pageNo);
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => this.getPageableUsers(this.paginator.pageIndex))
      )
      .subscribe();
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(UserRolesComponent, {
      width: '300px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event == 'Update') {
        this.updateRowData(result.data);
      } else if (result.event == 'Delete') {
        this.deleteRowData(result.data);
      }
    });
  }

  getPageableUsers(pageNo: number) {
    this.userService.getPageable(pageNo).subscribe(
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

  updateRowData(user) {
    this.userService.edit(user.id, user).subscribe(
      (data: any) => {
        this.getPageableUsers(this.pageNo);
        this.dataSource = this.dataSource.filter((value, key) => {
          if (value.id === data.id) {
            value.roles = data.roles;
          }
          return true;
        });
      },
      (error) => {
        alert(error);
      }
    );
  }
  deleteRowData(user) {
    this.userService.delete(user.id).subscribe(
      (data: any) => {
        this.getPageableUsers(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }

}