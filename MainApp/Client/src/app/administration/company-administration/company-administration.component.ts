import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';
import { UserRolesComponent } from '../user-administration/user-roles/user-roles.component';
import { CompanyService } from 'src/app/services/company.service';
import { NewCompanyComponent } from './new-company/new-company.component';

@Component({
  selector: 'app-company-administration',
  templateUrl: './company-administration.component.html',
  styleUrls: ['./company-administration.component.css']
})
export class CompanyAdministrationComponent implements OnInit {

  displayedColumns: string[] = ['companyId', 'name', 'cid'];
  dataSource: any[];


  @ViewChild(MatTable, { static: true }) table: MatTable<any>;

  constructor(public dialog: MatDialog,
              private companyService: CompanyService) {
    this.getCompanies();
  }

  ngOnInit(): void {
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(NewCompanyComponent, {
      width: '300px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event === 'Add') {
        this.addCompany(result.data);
      }
    });
  }

  getCompanies() {
    this.companyService.getAll().subscribe(
      (data: any) => {
        this.dataSource = data;
      },
      (error) => {
        alert(error);
      }
    );
  }

  addCompany(data) {
    let company = {
      'name': data.name,
      'cid': data.cid
    }
    this.companyService.add(company).subscribe(
      (data: any) => {
        this.getCompanies();
      },
      (error) => {
        alert(error);
      }
    );
  }
}
