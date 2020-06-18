import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VehicleAdministrationComponent } from './vehicle-administration.component';


const routes: Routes = [
  {
    path: "",
    component: VehicleAdministrationComponent,
    canActivate: [],
    children: []
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VehicleAdministrationRoutingModule { }
