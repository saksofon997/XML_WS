import { ReviewComponent } from './../../shared/review/review.component';
import { Component, OnInit, Input } from '@angular/core';
import { Car } from 'src/app/models/Car.model';
@Component({
  selector: 'app-car-teaser',
  templateUrl: './car-teaser.component.html',
  styleUrls: ['./car-teaser.component.css']
})
export class CarTeaserComponent implements OnInit {
@Input() car: Car;
  constructor() { }

  ngOnInit() {
  }

  checkMileage(mileage){
    return mileage != -1 ? mileage : "Unlimited";
  }

  addToCart($event){
    $event.stopPropagation();
    console.log("Added to cart");
  }
}
