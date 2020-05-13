import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';


const routes: Routes = [
  // Lazy loading administration
  { path: 'administration', loadChildren: () => import('./administration/administration.module').then(m => m.AdministrationModule)},
  { path: 'vehicle', loadChildren: () => import('./vehicle-page/vehicle-page.module').then(m => m.VehiclePageModule)},
  { path: 'rentals', loadChildren: () => import('./rentals/rentals.module').then(m => m.RentalsModule)},
  { path: 'vehicle-administration', loadChildren: () => import('./vehicle-administration/vehicle-administration.module').then(m => m.VehicleAdministrationModule)},
  { path: 'shopping-cart', loadChildren: () => import('./shopping-cart/shopping-cart.module').then(m => m.ShoppingCartModule)},

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
