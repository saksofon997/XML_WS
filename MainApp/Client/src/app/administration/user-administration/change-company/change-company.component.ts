import { Component, OnInit, Optional, Inject } from '@angular/core';
import { CompanyService } from 'src/app/services/company.service';
import { User } from 'src/app/models/User.model';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NewReviewDialogboxComponent } from 'src/app/shared/new-review-dialogbox/new-review-dialogbox.component';

@Component({
  selector: 'app-change-company',
  templateUrl: './change-company.component.html',
  styleUrls: ['./change-company.component.css']
})
export class ChangeCompanyComponent{

  local_data: User;
  companies: any;

  selectedCompany: any;
  currentCompany: any

  constructor(
    public dialogRef: MatDialogRef<NewReviewDialogboxComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: User,
    private companyService: CompanyService) {

    this.local_data = { ...data };

    this.currentCompany = this.local_data.company ? this.local_data.company : "None";

    this.companyService.getAll().subscribe(
      (data: any) => {
        this.companies = data;
      },
      (error) => {
        alert(error);
      }
    );
  }

  doAction() {
    this.local_data.company = this.selectedCompany;

    this.dialogRef.close({ data: this.local_data });
  }

  closeDialog() {
    this.dialogRef.close({ event: 'Cancel' });
  }

}
