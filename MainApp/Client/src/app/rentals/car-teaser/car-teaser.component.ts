import { ReviewComponent } from './../../shared/review/review.component';
import { Component, OnInit, Input } from '@angular/core';
import { Car } from 'src/app/models/Car.model';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';
import { ShoppingCart } from 'src/app/models/ShoppingCart.model';
import { RentalFront } from 'src/app/models/Rental.model';
import { UserService } from 'src/app/services/user.service';
import { ShoppingCartService } from 'src/app/services/shopping-cart.service';

@Component({
  selector: 'app-car-teaser',
  templateUrl: './car-teaser.component.html',
  styleUrls: ['./car-teaser.component.css']
})
export class CarTeaserComponent implements OnInit {
  @Input() car: Car;
  @Input() from: number;
  @Input() to: number;
  API_URL = environment.API_URL;

  constructor(private cookieService: CookieService,
    private shoppingCartService: ShoppingCartService,
    private userService: UserService,) {

  }

  ngOnInit() {
  }

  checkMileage(mileage) {
    return mileage != -1 ? mileage : 'Unlimited';
  }

  inCart() {
    let cart = this.shoppingCartService.getShoppingCart();
    if (cart.rentals) {
      return cart.rentals.some(e => (e.car.id === this.car.id && e.from === this.from && e.to === this.to));
    } else {
      return false;
    }
  }

  addToCart($event) {
    $event.stopPropagation();
    //TODO start - end time
    if (!this.userService.checkLoggedIn()) {
      alert("Please log in");
      return;
    }
    let rental = new RentalFront(null, this.car, this.from, this.to, null, null, null, this.car.ownerId);
    rental.customerId = this.userService.getUser().id;
    this.shoppingCartService.addRentalToCart(rental);
  }
}
