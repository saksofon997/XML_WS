import { Component, OnInit, ViewChild, ElementRef, HostListener } from '@angular/core';

@Component({
  selector: 'app-vehicle-page',
  templateUrl: './vehicle-page.component.html',
  styleUrls: ['./vehicle-page.component.css']
})
export class VehiclePageComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  @ViewChild('overview') overviewElement: ElementRef;
  @ViewChild('features') featuresElement: ElementRef;
  @ViewChild('reviews') reviewsElement: ElementRef;
  @ViewChild('location') locationElement: ElementRef;

  public currentActive = 0;
  public overviewOffset: Number = null;
  public featuresOffset: Number = null;
  public reviewsOffset: Number = null;
  public locationOffset: Number = null;

  ngAfterViewInit() {
    this.overviewOffset = this.overviewElement.nativeElement.offsetTop - 100;
    this.featuresOffset = this.featuresElement.nativeElement.offsetTop- 100;
    this.reviewsOffset = this.reviewsElement.nativeElement.offsetTop- 100;
    this.locationOffset = this.locationElement.nativeElement.offsetTop;
  }

  scrollToElement(test) {
    // scrollToElement Code :)
  }

  @HostListener('window:scroll', ['$event'])
  checkOffsetTop() {
    if (window.pageYOffset >= this.overviewOffset && window.pageYOffset < this.featuresOffset) {
      this.currentActive = 1;
    } else if (window.pageYOffset >= this.featuresOffset && window.pageYOffset < this.reviewsOffset) {
      this.currentActive = 2;
    } else if (window.pageYOffset >= this.reviewsOffset && window.pageYOffset < this.locationOffset) {
      this.currentActive = 3;
    } else if (window.pageYOffset >= this.locationOffset) {
      this.currentActive = 4;
    } else {
      this.currentActive = 0;
    }
  }

}
