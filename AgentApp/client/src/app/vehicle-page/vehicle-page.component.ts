import { VehicleService } from './../services/vehicle.service';
import { Component, OnInit, ViewChild, ElementRef, HostListener } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Car } from '../models/Car.model';
import { environment } from 'src/environments/environment';
import { Review } from '../models/Review.model';
import { ReviewService } from '../services/review.service';
import { LocationService } from '../services/location.service';

@Component({
  selector: 'app-vehicle-page',
  templateUrl: './vehicle-page.component.html',
  styleUrls: ['./vehicle-page.component.css']
})
export class VehiclePageComponent implements OnInit {

  imageSize: Object = { width: '400px', height: '300px', space: 4 }
  images: Array<Object>;

  coordinates: Number[];
  vehicleID: number;
  tripStartDate: string;
  tripEndDate: string;
  tripStartTime: string;
  tripEndTime: string;
  vehicle: Car;
  reservationInfo: any;

  reviewsList: any;

  constructor(private activatedRoute: ActivatedRoute,
    private vehicleService: VehicleService,
    private reviewService: ReviewService,
    private locationService: LocationService) {

    this.activatedRoute.queryParams.subscribe(params => {
      this.vehicleID = +params['vehicleID'];
      this.tripStartDate = params['startDate'];
      this.tripEndDate = params['endDate'];
      this.tripStartTime = params['startTime'];
      this.tripEndTime = params['endTime'];

      this.images = new Array();
    });

    this.getLocation(this.vehicleID);

    this.loadData();
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

  loadData() {
    let promise = new Promise((resolve, reject) => {
      this.getVehicle(this.vehicleID).then(() => {
        this.getVehicleReviews(this.vehicleID).then(() => {
          resolve();
        }, () => reject());
      }, () => reject());

    });
    return promise;
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

  getVehicleReviews(vehicleId: number) {
    let promise = new Promise((resolve, reject) => {
      this.reviewService.getByVehicle(vehicleId).subscribe(
        (data: Array<Review>) => {
          this.reviewsList = data;
          resolve();
        },
        (error) => {
          console.log(error);
          reject();
        }
      );
    });
    return promise;
  }

  getVehicle(vehicleID: number) {
    let promise = new Promise((resolve, reject) => {
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
          resolve();
        },
        (error) => {
          console.log(error);
          reject();
        }
      )
    });
    return promise;
  }

  getLocation(vehicleID: number) {
    let promise = new Promise((resolve, reject) => {
      this.locationService.locate(vehicleID).subscribe(
        (data: any) => {
          let location = [0, 0];
          location[0] = Number(data.lat);
          location[1] = Number(data.long);

          this.coordinates = location;

          resolve();
        },
        (error) => {
          console.log(error);
          reject();
        }
      )
    });
    return promise;
  }
}
