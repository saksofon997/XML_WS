import { VehicleService } from './../services/vehicle.service';
import { Component, OnInit, ViewChild, ElementRef, HostListener } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Car } from '../models/Car.model';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-vehicle-page',
  templateUrl: './vehicle-page.component.html',
  styleUrls: ['./vehicle-page.component.css']
})
export class VehiclePageComponent implements OnInit {

  imageSize: Object = {width: '400px', height: '300px', space: 4}
  images: Array<object> = [
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
  //location: Number[] = [37.587874, 55.73367];
  vehicleID: string;
  tripStartDate: string;
  tripEndDate: string;
  tripStartTime: string;
  tripEndTime: string;
  vehicle: Car;
  reservationInfo:any;

  constructor(private activatedRoute: ActivatedRoute,
    private vehicleService: VehicleService) {
    this.activatedRoute.queryParams.subscribe(params => {
          this.vehicleID = params['vehicleID'];
          this.tripStartDate = params['startDate'];
          this.tripEndDate = params['endDate'];
          this.tripStartTime = params['startTime'];
          this.tripEndTime = params['endTime'];
          console.log(this.vehicleID); 
          this.getVehicle(this.vehicleID);
      });
  }

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
  API_URL = environment.API_URL;
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
  getVehicle(vehicleID: string){
    this.vehicleService.getOne(vehicleID).subscribe(
      (data: any) => { 
        this.vehicle = data;
        this.reservationInfo = {
          tripStartDate: this.tripStartDate,
          tripEndDate: this.tripEndDate,
          tripStartTime: this.tripStartTime,
          tripEndTime: this.tripEndTime,
          price: this.vehicle.pricelist.pricePerDay
        }
        this.vehicle.images.forEach(imageName => {
          this.images.push({
            image: this.API_URL + "/vehicle/image/" + imageName,
            thumbImage: this.API_URL + "/vehicle/image/" + imageName
          })
        });
        },
      (error) => { alert(error.message);
      console.log(error)  }
    )
  }
}
