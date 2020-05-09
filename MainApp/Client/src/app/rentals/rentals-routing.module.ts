import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RentalsComponent } from './rentals.component';

const routes: Routes = [
  {
    path: "",
    component: RentalsComponent,
    canActivate: [],
    children: [

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RentalsRoutingModule { } 