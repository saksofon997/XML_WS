import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';

import { VehiclePageComponent } from './vehicle-page.component';
import { VehiclePageRoutingModule } from './vehicle-page-routing.module';
import { ReservationBoxComponent } from './reservation-box/reservation-box.component';

@NgModule({
  declarations: [ // Declare all components of module
    VehiclePageComponent,
    ReservationBoxComponent
  ],
  imports: [ // Import anything you would need to use in this module (froms...)
    CommonModule,
    MatDatepickerModule,
    MatNativeDateModule ,
    MatSelectModule,
    MatInputModule,


    VehiclePageRoutingModule,
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ], 
  // Exports should not be in lazy loaded modules
  providers: [
    MatDatepickerModule
  ]
})
export class VehiclePageModule {} 