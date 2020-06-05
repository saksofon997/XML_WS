import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-reservation-box',
  templateUrl: './reservation-box.component.html',
  styleUrls: ['./reservation-box.component.css']
})
export class ReservationBoxComponent implements OnInit {
@Input() priceAndReservation:any;

  constructor() { 
    console.log(this.priceAndReservation);
  }

  ngOnInit() {
    // this.priceAndReservation.tripStartDate = new Date(this.priceAndReservation.tripStartDate);
    // this.priceAndReservation.tripEndDate = new Date(this.priceAndReservation.tripEndDate);
    // this.priceAndReservation.tripStartTime = new Date(this.priceAndReservation.tripStartTime);
    // this.priceAndReservation.tripEndTime = new Date(this.priceAndReservation.tripEndTime);
    console.log(this.priceAndReservation);
  }

}
