import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgImageSliderModule } from 'ng-image-slider';
import { AngularYandexMapsModule } from 'angular8-yandex-maps';

import { VehiclePageComponent } from './vehicle-page.component';
import { VehiclePageRoutingModule } from './vehicle-page-routing.module';
import { ReservationBoxComponent } from './reservation-box/reservation-box.component';
import { VehicleInfoComponent } from './vehicle-info/vehicle-info.component';
import { SharedModule } from '../shared/shared.module';
import { VehicleAdministrationComponent } from './vehicle-administration/vehicle-administration.component';
import { OccupancyViewComponent } from './occupancy-view/occupancy-view.component';
import { OccupancyDialogBoxComponent } from './occupancy-dialog-box/occupancy-dialog-box.component';
import { MaterialFileInputModule } from 'ngx-material-file-input';


@NgModule({
  declarations: [ // Declare all components of module
    VehiclePageComponent,
    ReservationBoxComponent,
    VehicleInfoComponent,
    VehicleAdministrationComponent,
    OccupancyViewComponent,
    OccupancyDialogBoxComponent,

  ],
  imports: [ // Import anything you would need to use in this module (froms...)
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    // material
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    MatTableModule,
    MatDialogModule,
    MatFormFieldModule,
    MaterialFileInputModule,
    
    NgImageSliderModule,
    AngularYandexMapsModule.forRoot("396fefe7-95c2-486a-ae3e-c8f062813962"),

    VehiclePageRoutingModule,
    SharedModule,
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  // Exports should not be in lazy loaded modules
  providers: [
    MatDatepickerModule,
  ]
})
export class VehiclePageModule { }
