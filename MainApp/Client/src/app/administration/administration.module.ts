import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SideListComponent } from './side-list/side-list.component';
import { AdminTableComponent } from './admin-table/admin-table.component';
import { AdministrationRoutingModule } from './administration-routing.module';
import { AdministrationMainComponent } from './administration-main/administration-main.component';

@NgModule({
  declarations: [ // Declare all components of module
    AdministrationMainComponent,
    SideListComponent,
    AdminTableComponent
  ],
  imports: [ // Import anything you would need to use in this module (froms...)
    CommonModule,
    AdministrationRoutingModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ], 
  // Exports should not be in lazy loaded modules
})
export class AdministrationModule {}