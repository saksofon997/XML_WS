import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SideListComponent } from './side-list/side-list.component';
import { AdministrationRoutingModule, routingComponents } from './administration-routing.module';
import { AdministrationMainComponent } from './administration-main/administration-main.component';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { DialogBoxComponent } from './dialog-box-edit/dialog-box-edit.component';
import { MatIconModule } from '@angular/material/icon'

@NgModule({
  declarations: [ // Declare all components of module
    AdministrationMainComponent,
    SideListComponent,
    routingComponents,
    DialogBoxComponent,
  ],
  imports: [ // Import anything you would need to use in this module (forms...)
    CommonModule,
    FormsModule,
    MatTableModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    AdministrationRoutingModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ], 
  // Exports should not be in lazy loaded modules
})
export class AdministrationModule {}
