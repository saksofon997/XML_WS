import { Component, OnInit, Input } from '@angular/core';
import { Car } from 'src/app/models/Car.model';
import { RentalFront } from 'src/app/models/Rental.model';
import { Bundle } from 'src/app/models/Bundle.model';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-sc-car-teaser',
  templateUrl: './sc-car-teaser.component.html',
  styleUrls: ['./sc-car-teaser.component.css']
})
export class ScCarTeaserComponent implements OnInit {

  @Input() bundles: Array<Bundle>;
  @Input() rental: RentalFront;

  constructor(private cookieService: CookieService) { }

  ngOnInit() {
  }

  addToBundle(bundle) {
    if ((bundle.owner) && bundle.owner !== this.rental.car.ownerId) {
      alert('Cant add to bundle bla bla bla... prettier alert needed');
      return;
    }
    bundle.owner = this.rental.car.ownerId;
    bundle.rentals.push(this.rental);
    this.rental.bundle = bundle;
  }

  fromLocaleString() {
    let date = new Date(this.rental.from * 1000);
    return date.toLocaleString();
  }

  toLocaleString() {
    let date = new Date(this.rental.to * 1000);
    return date.toLocaleString();
  }

}
