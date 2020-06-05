import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RentalsComponent } from './rentals.component';
import { RentalsRoutingModule } from './rentals-routing.module';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import {MatAutocompleteModule} from '@angular/material/autocomplete'
import { AngularYandexMapsModule } from 'angular8-yandex-maps';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SelectDropDownModule } from 'ngx-select-dropdown';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatListModule} from '@angular/material/list';
import { VehiclePageModule } from '../vehicle-page/vehicle-page.module';
import { CarTeaserComponent } from './car-teaser/car-teaser.component';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from '../shared/shared.module';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import {MatExpansionModule} from '@angular/material/expansion';

@NgModule({
  imports: [
    CommonModule,
    RentalsRoutingModule,
    MatDatepickerModule,
    MatNativeDateModule ,
    MatSelectModule,
    MatInputModule,
    MatIconModule,
    AngularYandexMapsModule.forRoot("73d3c687-6c0b-4a6a-9d44-865e790fb034"),
    FormsModule,
    SelectDropDownModule,
    ReactiveFormsModule,
    MatAutocompleteModule, 
    MatCheckboxModule,
    MatListModule,
    MatTooltipModule,
    VehiclePageModule,
    NgbRatingModule,
    SharedModule,
    MatExpansionModule
  ],
  declarations: [RentalsComponent,
    CarTeaserComponent
    ]
})
export class RentalsModule { }
