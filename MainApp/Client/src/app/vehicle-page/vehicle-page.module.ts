import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgImageSliderModule } from 'ng-image-slider';

import { VehiclePageComponent } from './vehicle-page.component';
import { VehiclePageRoutingModule } from './vehicle-page-routing.module';
import { ReservationBoxComponent } from './reservation-box/reservation-box.component';
import { VehicleInfoComponent } from './vehicle-info/vehicle-info.component';
import { SharedModule } from '../shared/shared.module';
import { VehicleAdministrationComponent } from './vehicle-administration/vehicle-administration.component';

@NgModule({
  declarations: [ // Declare all components of module
    VehiclePageComponent,
    ReservationBoxComponent,
    VehicleInfoComponent,
    VehicleAdministrationComponent,
  ],
  imports: [ // Import anything you would need to use in this module (froms...)
    CommonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    NgImageSliderModule,
    FormsModule,
    ReactiveFormsModule,

    VehiclePageRoutingModule,
    SharedModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  // Exports should not be in lazy loaded modules
  providers: [
    MatDatepickerModule,
  ]
})
export class VehiclePageModule { } 