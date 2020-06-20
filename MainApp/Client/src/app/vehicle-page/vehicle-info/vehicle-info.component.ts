import { Component, OnInit, Input } from '@angular/core';
import { Car } from 'src/app/models/Car.model';

@Component({
  selector: 'app-vehicle-info',
  templateUrl: './vehicle-info.component.html',
  styleUrls: ['./vehicle-info.component.css']
})
export class VehicleInfoComponent implements OnInit {

  @Input() vehicle: Car;
  constructor() { }

  ngOnInit() {
  }
  
  checkAvailableMileage(mileage){
    return mileage != -1 ? mileage : "Unlimited";
  }
}
