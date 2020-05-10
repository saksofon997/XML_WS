import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';

import { VehicleAdministrationRoutingModule } from './vehicle-administration-routing.module';
import { VehicleAdministrationComponent } from './vehicle-administration.component';
import { PricelistTableComponent } from './pricelist-table/pricelist-table.component';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { PricelistDialogBoxComponent } from './pricelist-dialog-box/pricelist-dialog-box.component';


@NgModule({
  declarations: [
    VehicleAdministrationComponent,
    PricelistTableComponent,
    PricelistDialogBoxComponent,
  ],
  imports: [
    CommonModule,
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
    VehicleAdministrationRoutingModule,
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
 ],
})
export class VehicleAdministrationModule { }
