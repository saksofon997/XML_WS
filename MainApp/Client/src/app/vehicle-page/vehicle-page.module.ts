import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehiclePageComponent } from './vehicle-page.component';
import { VehiclePageRoutingModule } from './vehicle-page-routing.module';


@NgModule({
  declarations: [ // Declare all components of module
    VehiclePageComponent
  ],
  imports: [ // Import anything you would need to use in this module (froms...)
    CommonModule,
    VehiclePageRoutingModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ], 
  // Exports should not be in lazy loaded modules
})
export class VehiclePageModule {} 