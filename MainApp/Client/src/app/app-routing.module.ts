import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';


const routes: Routes = [
  // Lazy loading administration
  { path: 'administration', loadChildren: () => import('./administration/administration.module').then(m => m.AdministrationModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
/*Add all routing objects to this array*/
export const routingComponents = []
