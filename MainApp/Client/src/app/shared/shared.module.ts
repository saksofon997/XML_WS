import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';

import { ReviewComponent } from './review/review.component';
import { CarTeaserCarInfoComponent } from './car-teaser-car-info/car-teaser-car-info.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MaterialFileInputModule } from 'ngx-material-file-input';
import { RentalTeaserComponent } from './rental-teaser/rental-teaser.component';
import { NewReviewDialogboxComponent } from './new-review-dialogbox/new-review-dialogbox.component';
import { NewRentalReportDialogboxComponent } from './new-rental-report-dialogbox/new-rental-report-dialogbox.component';

// import this module in all others which use these shared components and modules
@NgModule({
  declarations: [
    ReviewComponent,
    CarTeaserCarInfoComponent,
    RentalTeaserComponent,
    NewReviewDialogboxComponent,
    NewRentalReportDialogboxComponent,
    // declare components to export them to other modules e.g. AlertComponent
  ],
  imports: [
    // import all angular modules which are used in more than one other module
    CommonModule,
    NgbRatingModule,
    MatIconModule,
    MatTooltipModule,
    FormsModule,
    ReactiveFormsModule,

    //material
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    MatTableModule,
    MatDialogModule,
    MatFormFieldModule,
    MaterialFileInputModule,
  ],
  exports: [
    // export all of the above
    // CommonModule,
    ReviewComponent,
    CarTeaserCarInfoComponent,
    RentalTeaserComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class SharedModule { }
