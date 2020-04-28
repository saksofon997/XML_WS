import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// import this module in all others which use these shared components and modules
@NgModule({
  declarations: [
    // declare components to export them to other modules e.g. AlertComponent
  ],
  imports: [
    // import all angular modules which are used in more than one other module
    CommonModule,
  ],
  exports: [
    // export all of the above
    CommonModule,
  ]
})
export class SharedModule { }