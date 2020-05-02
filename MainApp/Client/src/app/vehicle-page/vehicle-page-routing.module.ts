import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VehiclePageComponent } from './vehicle-page.component';

const routes: Routes = [
  {
    path: "", 
    component: VehiclePageComponent,
    canActivate: [],
    children: [
      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VehiclePageRoutingModule { } 