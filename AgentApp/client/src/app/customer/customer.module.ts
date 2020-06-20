import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';

import { CustomerRentalsComponent } from './customer-rentals/customer-rentals.component';
import { CustomerRentalsListingComponent } from './customer-rentals-listing/customer-rentals-listing.component';
import { CustomerRoutingModule } from './customer-routing.module';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    CustomerRentalsComponent,
    CustomerRentalsListingComponent,
  ],
  imports: [ // Import anything you would need to use in this module (forms...)
    CommonModule,
    FormsModule,

    MatTabsModule,
    FormsModule,
    MatTableModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,

    SharedModule,
    CustomerRoutingModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  // Exports should not be in lazy loaded modules
})
export class CustomerModule { }
