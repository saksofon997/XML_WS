import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Car } from 'src/app/models/Car.model';
import { RentalFront } from 'src/app/models/Rental.model';
import { Bundle } from 'src/app/models/Bundle.model';
import { CookieService } from 'ngx-cookie-service';
import { ShoppingCartService } from 'src/app/services/shopping-cart.service';

@Component({
  selector: 'app-sc-car-teaser',
  templateUrl: './sc-car-teaser.component.html',
  styleUrls: ['./sc-car-teaser.component.css']
})
export class ScCarTeaserComponent implements OnInit {

  @Input() bundles: Array<Bundle>;
  @Input() rental: RentalFront;
  @Output() delete: EventEmitter<RentalFront> = new EventEmitter();
  @Output() cartUpdated: EventEmitter<RentalFront> = new EventEmitter();

  constructor(private cookieService: CookieService,
    private shoppingCartService: ShoppingCartService) { }

  ngOnInit() {
  }

  addToBundle(bundle) {
    if ((bundle.owner) && bundle.owner !== this.rental.car.ownerId) {
      alert('Cars in the same bundle must be from the same owner.');
      return;
    }
    this.shoppingCartService.addToBundle(bundle, this.rental);
    this.cartUpdated.emit(this.rental);
  }

  fromLocaleString() {
    let date = new Date(this.rental.from * 1000);
    return date.toLocaleString();
  }

  toLocaleString() {
    let date = new Date(this.rental.to * 1000);
    return date.toLocaleString();
  }

  removeFromCart(rental){
    if (rental.bundle) {
      var bundleTemp = new Bundle(rental.bundle, null, null);
      this.shoppingCartService.removeFromBundle(bundleTemp, rental);
    }
    this.cartUpdated.emit(this.rental);
    this.shoppingCartService.removeRentalFromCart(rental);
    this.delete.emit(this.rental);
  }

}
