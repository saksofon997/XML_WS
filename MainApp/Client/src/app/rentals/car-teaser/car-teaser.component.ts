import { ReviewComponent } from './../../shared/review/review.component';
import { Component, OnInit, Input } from '@angular/core';
import { Car } from 'src/app/models/Car.model';
import { CookieService } from 'ngx-cookie-service';
import { ShoppingCart } from 'src/app/models/ShoppingCart.model';
import { RentalFront } from 'src/app/models/Rental.model';
@Component({
  selector: 'app-car-teaser',
  templateUrl: './car-teaser.component.html',
  styleUrls: ['./car-teaser.component.css']
})
export class CarTeaserComponent implements OnInit {
  @Input() car: Car;
  @Input() from: number;
  @Input() to: number;

  constructor(private cookieService: CookieService) { }

  ngOnInit() {
  }

  checkMileage(mileage){
    return mileage != -1 ? mileage : "Unlimited";
  }

  addToCart($event){
    $event.stopPropagation();
    console.log("Added to cart");

    //let cart = JSON.parse(this.cookieService.get('shopping-cart'));

    console.log(this.from);
    console.log(this.to);
    if (this.cookieService.get('shopping-cart')) {
      let cart = JSON.parse(this.cookieService.get('shopping-cart'));

      if (!cart.rentals.some(e => e.car === this.car)) {
        let rental = new RentalFront();
        rental.car = this.car;
        rental.from = this.from;
        rental.to = this.to;
        cart.rentals.push(rental);
      }
      

      console.log(cart);
      this.cookieService.set('shopping-cart', JSON.stringify(cart));
    } else {
      let cart = new ShoppingCart();
      cart.rentals = new Array();

      let rental = new RentalFront();
      rental.car = this.car;
      rental.from = this.from;
      rental.to = this.to;
      cart.rentals.push(rental);

      console.log(cart);

      this.cookieService.set('shopping-cart', JSON.stringify(cart));
    }
    //this.cookieService.delete('shopping-cart');

  }
}
