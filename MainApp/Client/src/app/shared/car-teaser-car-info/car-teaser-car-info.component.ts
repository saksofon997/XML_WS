import { Component, OnInit, Input } from '@angular/core';
import { Car } from 'src/app/models/Car.model';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-car-teaser-car-info',
  templateUrl: './car-teaser-car-info.component.html',
  styleUrls: ['./car-teaser-car-info.component.css']
})
export class CarTeaserCarInfoComponent implements OnInit {

  @Input() car: Car;
  API_URL = environment.API_URL;

  constructor() { }

  ngOnInit() {
  }

  checkMileage(mileage){
    return mileage != -1 ? mileage : "Unlimited";
  }

}
