import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VehicleAdministrationRoutingModule } from './vehicle-administration-routing.module';
import { VehicleAdministrationComponent } from './vehicle-administration.component';


@NgModule({
  declarations: [VehicleAdministrationComponent],
  imports: [
    CommonModule,
    VehicleAdministrationRoutingModule
  ]
})
export class VehicleAdministrationModule { }
