import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Bundle } from '../models/Bundle.model';
import { RentalFront } from '../models/Rental.model';
import { ShoppingCart } from '../models/ShoppingCart.model';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {


  constructor(private cookieService: CookieService) {
    if (this.checkShoppingCart() === null) {
      let bundles = new Array<Bundle>();
      let rentals = new Array<RentalFront>();
      let cart = new ShoppingCart(rentals, bundles);
      localStorage.setItem('shopping-cart', JSON.stringify(cart));
    }
  }

  getShoppingCart() {
    if (this.checkShoppingCart() !== null) {
      return JSON.parse(localStorage.getItem('shopping-cart'));
    } else {
      let cart = JSON.parse(localStorage.getItem('shopping-cart'));
      cart.bundles = new Array<Bundle>();
      cart.rentals = new Array<RentalFront>();
      localStorage.setItem('shopping-cart', JSON.stringify(cart));
      console.log(cart);
      return cart;
    }
  }

  checkShoppingCart() {
    return JSON.parse(localStorage.getItem('shopping-cart'));
  }

  getRentals() {
    let cart = this.getShoppingCart();
    return cart.rentals;
  }

  getBundles() {
    let cart = this.getShoppingCart();
    return cart.bundles;
  }

  addRentalToCart(rental: RentalFront) {
    let cart = this.getShoppingCart();
    cart.rentals.push(rental);
    localStorage.setItem('shopping-cart', JSON.stringify(cart));
  }

  removeRentalFromCart(rental: RentalFront) {
    let cart = this.getShoppingCart();
    cart.rentals = cart.rentals.filter(x => !(x.car.id === rental.car.id && x.to === rental.to && x.from === rental.from));
    localStorage.setItem('shopping-cart', JSON.stringify(cart));
    
  }

  getRental(rental: RentalFront) {
    let cart = this.getShoppingCart();
    for (let rent of cart.rentals) {
      if (rent.car.id === rental.car.id && rent.to === rental.to && rent.from === rental.from) {
        return rent;
      }
    }
  }

  addBundleToCart(data: any) {
    let cart = this.getShoppingCart();
    if (!cart.bundles.some(e => e.name === data.name)) {
      let bundle = new Bundle(data.name, new Array<RentalFront>(), null);
      cart.bundles.push(bundle);
    }
    localStorage.setItem('shopping-cart', JSON.stringify(cart));
  }

  removeBundleFromCart(data: any) {
    let cart = this.getShoppingCart();
    var found = cart.bundles.find(b => {
      return b.name === data.name;
    });
    for (let rental of cart.rentals) {
      if (rental.bundle === found.name) {
        rental.bundle = null;
      }
    }
    cart.bundles = cart.bundles.filter((value) => {
      return value.name !== data.name;
    });
    localStorage.setItem('shopping-cart', JSON.stringify(cart));
  }

  addToBundle(bundl, rental) {
    let cart = this.getShoppingCart();
    var found = cart.bundles.find(b => {
      return b.name === bundl.name;
    });
    for (let rent of cart.rentals) {
      if (rent.car.id === rental.car.id && rent.to === rental.to && rent.from === rental.from) {
        rent.bundle = found.name;
        found.owner = rental.car.ownerId;
        found.rentals.push(rent);
      }
    }
    localStorage.setItem('shopping-cart', JSON.stringify(cart));
  }

  removeFromBundle(bundl: Bundle, rental) {
    let cart = this.getShoppingCart();
    for (let bundle of cart.bundles) {
      if (bundl.name === bundle.name) {
        bundle.rentals = bundle.rentals.filter((value) => {
          return !(value.car.id === rental.car.id && value.to === rental.to && value.from === rental.from);
        });

        if (bundle.rentals.length === 0) {
          bundle.owner = null;
        }
        break;
      }
    }
    for (let rent of cart.rentals) {
      if (rent.car.id === rental.car.id && rent.to === rental.to && rent.from === rental.from) {
        rent.bundle = null;
        break;
      }
    }
    localStorage.setItem('shopping-cart', JSON.stringify(cart));
  }

  clearCart() {
    let bundles = new Array<Bundle>();
    let rentals = new Array<RentalFront>();
    let cart = new ShoppingCart(rentals, bundles);
    localStorage.setItem('shopping-cart', JSON.stringify(cart));
    this.getShoppingCart();
  }
}
