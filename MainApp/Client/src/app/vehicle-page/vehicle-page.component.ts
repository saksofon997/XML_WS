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

  @ViewChild('home') homeElement: ElementRef;
  @ViewChild('con') conElement: ElementRef;
  @ViewChild('new') newElement: ElementRef;
  @ViewChild('about') aboutElement: ElementRef;

  public currentActive = 0;
  public homeOffset: Number = null;
  public conOffset: Number = null;
  public newOffset: Number = null;
  public aboutOffset: Number = null;

  ngAfterViewInit() {
    this.homeOffset = this.homeElement.nativeElement.offsetTop;
    this.conOffset = this.conElement.nativeElement.offsetTop;
    this.newOffset = this.newElement.nativeElement.offsetTop;
    this.aboutOffset = this.aboutElement.nativeElement.offsetTop;
  }

  scrollToElement(test) {
    // scrollToElement Code :)
  }

  @HostListener('window:scroll', ['$event'])
  checkOffsetTop() {
    if (window.pageYOffset >= this.homeOffset && window.pageYOffset < this.conOffset) {
      this.currentActive = 1;
    } else if (window.pageYOffset >= this.conOffset && window.pageYOffset < this.newOffset) {
      this.currentActive = 2;
    } else if (window.pageYOffset >= this.newOffset && window.pageYOffset < this.aboutOffset) {
      this.currentActive = 3;
    } else if (window.pageYOffset >= this.aboutOffset) {
      this.currentActive = 4;
    } else {
      this.currentActive = 0;
    }
  }

}
