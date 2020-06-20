import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CustomerRentalsComponent } from './customer-rentals/customer-rentals.component';

const routes: Routes = [
  {
    path: '',
    component: CustomerRentalsComponent,
    canActivate: [],
    children: [
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
