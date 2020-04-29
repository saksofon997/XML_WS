import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdministrationMainComponent } from './administration/administration-main/administration-main.component';


const routes: Routes = [
  {path: "administration", component: AdministrationMainComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
/*Add all routing objects to this array*/
export const routingComponents = [AdministrationMainComponent]
