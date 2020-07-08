import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';


const routes: Routes = [
  // Lazy loading administration
  { path: 'administration', loadChildren: () => import('./administration/administration.module').then(m => m.AdministrationModule) },
  { path: 'vehicle', loadChildren: () => import('./vehicle-page/vehicle-page.module').then(m => m.VehiclePageModule) },
  { path: 'rentals', loadChildren: () => import('./rentals/rentals.module').then(m => m.RentalsModule) },
  {
    path: 'vehicle-administration', loadChildren:
      () => import('./vehicle-administration/vehicle-administration.module').then(m => m.VehicleAdministrationModule)
  },
  { path: 'shopping-cart', loadChildren: () => import('./shopping-cart/shopping-cart.module').then(m => m.ShoppingCartModule) },
  { path: 'customer', loadChildren: () => import('./customer/customer.module').then(m => m.CustomerModule)},
  { path: 'chat', loadChildren: () => import('./chat-main/chat.module').then(m => m.ChatModule)},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }
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
