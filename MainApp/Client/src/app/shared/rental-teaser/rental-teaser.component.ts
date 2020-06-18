import { Component, OnInit, Input } from '@angular/core';
import { RentalFront } from 'src/app/models/Rental.model';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-rental-teaser',
  templateUrl: './rental-teaser.component.html',
  styleUrls: ['./rental-teaser.component.css']
})
export class RentalTeaserComponent implements OnInit {

  @Input() rental: RentalFront;
  @Input() status: string;
  @Input() customer: boolean;

  constructor(private cookieService: CookieService) { }

  ngOnInit() {
  }


  fromLocaleString() {
    const date = new Date(this.rental.from * 1000);
    return date.toLocaleString();
  }

  toLocaleString() {
    const date = new Date(this.rental.to * 1000);
    return date.toLocaleString();
  }

  sendMessage($event) {
    $event.stopPropagation();
    // TODO
  }

  newReview($event) {
    $event.stopPropagation();
    // TODO
  }

  approveRental($event) {
    $event.stopPropagation();
    // TODO
  }

  rejectRental($event) {
    $event.stopPropagation();
    // TODO
  }

  newReport($event) {
    $event.stopPropagation();
    // TODO
  }
}
