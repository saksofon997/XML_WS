import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdministrationMainComponent } from './administration-main/administration-main.component';

const routes: Routes = [
  {
    path: "", // Empty path, beacuse we are already on /administration route, check app-routing.module.ts
    component: AdministrationMainComponent,
    canActivate: [],
    children: [
      // might be needed for components with tables for different data
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministrationRoutingModule { }