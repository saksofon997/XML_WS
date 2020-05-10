import { Component, OnInit } from '@angular/core';
import { Car } from '../models/Car.model';
import { Review } from '../models/Review.model';
import { Rental } from '../models/Rental.model';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  rentals = new Array<Rental>();
  constructor() {
    var review = new Review(4, null, { id: null, name: "" },
    { id: null, name: "" },
    "");
    this.rentals.push(new Rental(new Car("https://article.images.consumerreports.org/f_auto/prod/content/dam/CRO%20Images%202018/Cars/November/CR-Cars-InlineHero-2019-Honda-Insight-driving-trees-11-18",
    "Jaguar", "I dont know", "Diesel", "Automatic", "A", 2, "Unlimited", 1000, 2, review),  new Date(1588712400000),  new Date(1588782400000)));
    this.rentals.push(new Rental(new Car("https://www.testoviautomobila.rs/wp-content/uploads/2015/05/fica-prelepa-slika-840x420.jpg",
    "Zastava", "500", "Gasoline", "Manual", "A", 15, "Unlimited", 5000, 5, review),  new Date(1588712400000),  new Date(1588782400000)));
  }

  ngOnInit() {
  }

}
