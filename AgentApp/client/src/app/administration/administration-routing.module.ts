import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdministrationMainComponent } from './administration-main/administration-main.component';
import { BrandTableComponent } from './brand-table/brand-table.component';
import { ModelTableComponent } from './model-table/model-table.component';
import { CategoriesTableComponent } from './categories-table/categories-table.component';
import { FuelTableComponent } from './fuel-table/fuel-table.component';
import { TransmissionTableComponent } from './transmission-table/transmission-table.component';
import { PendingReviewsComponent } from './pending-reviews/pending-reviews.component';

const routes: Routes = [
  {
    path: '',
    component: AdministrationMainComponent,
    canActivate: [],
    children: [
      { path: 'brands', component: BrandTableComponent },
      { path: 'brands/:brandId/models', component: ModelTableComponent },
      { path: 'categories', component: CategoriesTableComponent },
      { path: 'fuel', component: FuelTableComponent },
      { path: 'transmission', component: TransmissionTableComponent },
      { path: 'pending-reviews', component: PendingReviewsComponent },
      //{ path: 'users', component: Users },
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministrationRoutingModule { }
export const routingComponents = [BrandTableComponent, ModelTableComponent, CategoriesTableComponent, FuelTableComponent, TransmissionTableComponent, PendingReviewsComponent]
