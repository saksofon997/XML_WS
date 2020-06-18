import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { VehiclePageComponent } from './vehicle-page.component';
import { VehicleAdministrationComponent } from './vehicle-administration/vehicle-administration.component';

const routes: Routes = [
  {
    path: "",
    component: VehiclePageComponent,
    canActivate: [],
    children: []
  },
  {
    path: "edit/:id",
    component: VehicleAdministrationComponent,
    canActivate: [], // admin owns the vehicle
  },
  {
    path: "new",
    component: VehicleAdministrationComponent,
    canActivate: [], // user can add more vehicles
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VehiclePageRoutingModule { } 