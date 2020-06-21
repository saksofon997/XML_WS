import { Component, OnInit, Optional, Inject } from '@angular/core';
import { RoleService } from 'src/app/services/role.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface UsersData {
  name: string;
  id: number;
}

@Component({
  selector: 'app-user-roles',
  templateUrl: './user-roles.component.html',
  styleUrls: ['./user-roles.component.css']
})
export class UserRolesComponent {

  roles: Array<any> = [
    { id: 1560608769632, name: 'Empty' }
  ];
  form: FormGroup;

  action: string;
  local_data: any;

  constructor(private roleService: RoleService,
    private formBuilder: FormBuilder,

    public dialogRef: MatDialogRef<UserRolesComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: UsersData
  ) {
    this.local_data = { ...data };
    this.action = this.local_data.action;

    this.form = this.formBuilder.group({
      role: ['', [Validators.required]]
    });

    this.roleService.getAll().subscribe(
      (data: any) => {
        this.roles = data;
      },
      (error) => {
        alert(error);
      }
    );
  }

  onSubmit(form) {}

  doAction() {
    this.local_data.roles = [this.form.controls.role.value];
    this.dialogRef.close({ event: this.action, data: this.local_data });
  }

  closeDialog() {
    this.dialogRef.close({ event: 'Cancel' });
  }

}
