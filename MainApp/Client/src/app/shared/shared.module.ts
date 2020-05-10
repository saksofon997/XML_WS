import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReviewComponent } from './review/review.component';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { CarTeaserCarInfoComponent } from './car-teaser-car-info/car-teaser-car-info.component';
 
// import this module in all others which use these shared components and modules
@NgModule({
  declarations: [
    ReviewComponent,
    CarTeaserCarInfoComponent
    // declare components to export them to other modules e.g. AlertComponent
  ],
  imports: [
    // import all angular modules which are used in more than one other module
    CommonModule,
    NgbRatingModule,
  ],
  exports: [
    // export all of the above
    //CommonModule,
    ReviewComponent,
    CarTeaserCarInfoComponent
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ], 
})
export class SharedModule { }