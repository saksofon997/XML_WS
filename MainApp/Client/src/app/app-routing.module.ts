import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';


const routes: Routes = [
  // Lazy loading administration
  { path: 'administration', loadChildren: () => import('./administration/administration.module').then(m => m.AdministrationModule)},
  { path: 'vehicle', loadChildren: () => import('./vehicle-page/vehicle-page.module').then(m => m.VehiclePageModule)},
  { path: 'rentals', loadChildren: () => import('./rentals/rentals.module').then(m => m.RentalsModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    preloadingStrategy: PreloadAllModules,
    anchorScrolling: 'enabled',
    onSameUrlNavigation: 'reload',
    scrollPositionRestoration: 'enabled',
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
/*Add all routing objects to this array*/
export const routingComponents = []
