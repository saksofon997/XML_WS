import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';

import { ShoppingCartRoutingModule } from './shopping-cart-routing.module';
import { SharedModule } from '../shared/shared.module';
import { ShoppingCartComponent } from './shopping-cart.component';
import { ScCarTeaserComponent } from './sc-car-teaser/sc-car-teaser.component';


@NgModule({
  declarations: [ 
    ShoppingCartComponent,
    ScCarTeaserComponent,
  ],
  imports: [ 
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    ShoppingCartRoutingModule,
    SharedModule,
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  
  providers: [
    
  ]
})
export class ShoppingCartModule { }
