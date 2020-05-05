import { Component, OnInit, ViewChild, ElementRef, HostListener } from '@angular/core';

@Component({
  selector: 'app-vehicle-page',
  templateUrl: './vehicle-page.component.html',
  styleUrls: ['./vehicle-page.component.css']
})
export class VehiclePageComponent implements OnInit {

  imageSize: Object = {width: '400px', height: '300px', space: 4}
  images: Array<object> = [{
    image: 'https://lh3.googleusercontent.com/proxy/ALI_tszGz9qA4ptTF653t2ftXcO08M32XZ_9JMSCTVyT1uQ0FZCWOGuq-xnXiPYlKfV5-f3Sj-vg9OuSiOPHsY6bJpLG2RAQXwUKl3GNH5OPL7ClRrg',
    thumbImage: 'https://lh3.googleusercontent.com/proxy/ALI_tszGz9qA4ptTF653t2ftXcO08M32XZ_9JMSCTVyT1uQ0FZCWOGuq-xnXiPYlKfV5-f3Sj-vg9OuSiOPHsY6bJpLG2RAQXwUKl3GNH5OPL7ClRrg'
  },
  {
    image: 'https://i.ytimg.com/vi/H19PR3N8QI0/hqdefault.jpg',
    thumbImage: 'https://i.ytimg.com/vi/H19PR3N8QI0/hqdefault.jpg'
  },
  {
    image: 'https://previews.123rf.com/images/jarino47/jarino471708/jarino47170800397/84205780-soviet-tank-t-34-in-valley-of-death-dukla-paas-from-world-war-ii-in-svidnik-slovakia.jpg',
    thumbImage: 'https://previews.123rf.com/images/jarino47/jarino471708/jarino47170800397/84205780-soviet-tank-t-34-in-valley-of-death-dukla-paas-from-world-war-ii-in-svidnik-slovakia.jpg'
  },
  {
    image: 'https://i2.wp.com/asiatimes.com/wp-content/uploads/2019/01/T-34-in-snow.jpg?fit=1600%2C1067&ssl=1',
    thumbImage: 'https://i2.wp.com/asiatimes.com/wp-content/uploads/2019/01/T-34-in-snow.jpg?fit=1600%2C1067&ssl=1'
  }
  ];

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
    this.featuresOffset = this.featuresElement.nativeElement.offsetTop - 100;
    this.reviewsOffset = this.reviewsElement.nativeElement.offsetTop - 100;
    this.locationOffset = this.locationElement.nativeElement.offsetTop - 100;
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
