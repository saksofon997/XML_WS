import { Component, OnInit, Input } from '@angular/core';
import { Car } from 'src/app/models/Car.model';
import { Rental } from 'src/app/models/Rental.model';

@Component({
  selector: 'app-sc-car-teaser',
  templateUrl: './sc-car-teaser.component.html',
  styleUrls: ['./sc-car-teaser.component.css']
})
export class ScCarTeaserComponent implements OnInit {

  @Input() rental: Rental;

  constructor() { }

  ngOnInit() {
  }

}
