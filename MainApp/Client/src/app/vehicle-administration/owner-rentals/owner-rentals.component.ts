import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-owner-rentals',
  templateUrl: './owner-rentals.component.html',
  styleUrls: ['./owner-rentals.component.css']
})
export class OwnerRentalsComponent implements OnInit {

  status: string;

  constructor() {
    this.status = 'PENDING';
  }

  ngOnInit() {
  }

  changeStatus(status) {
    this.status = status;
  }

}
