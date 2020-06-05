import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-reservation-box',
  templateUrl: './reservation-box.component.html',
  styleUrls: ['./reservation-box.component.css']
})
export class ReservationBoxComponent implements OnInit {
@Input() priceAndReservation:any;

  constructor() { }

  ngOnInit() {
  }

}
