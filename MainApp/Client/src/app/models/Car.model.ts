import { Brand } from './Brand.model';
import { Category } from './Category.model';
import { Model } from './Model.model';
import { Fuel } from './Fuel.model';
import { Transmission } from './Transmission.model';
import { Pricelist } from './Pricelist.model';

export class Car {
  id: number;
  ownerId: number;
  images: string[];
  brand: Brand;
  model: Model;
  category: Category;
  fuel: Fuel;
  transmission: Transmission;
  seats: number;
  childSeats: number;
  mileage: number;
  cdw: boolean;
  pricelist: Pricelist;
  numberOfStars: number;
  numberOfReviews: number;
  availableMileage: number;

  // constructor(id: number, ownerId: number, images: string[], brand: Brand,
  //   model: Model, category: Category, fuel: Fuel, transmission: Transmission,
  //   seats: number, childSeats: number, mileage: number, cdw: boolean,
  //   pricelist: Pricelist, numberOfStars: number, numberOfReviews: number, availableMileage: number) {
  //   this.id = id;
  //   this.ownerId = ownerId;
  //   this.images = images;
  //   this.brand = brand;
  //   this.model = model;
  //   this.category = category;
  //   this.fuel = fuel;
  //   this.transmission = transmission;
  //   this.seats = seats;
  //   this.childSeats = childSeats;
  //   this.mileage = mileage;
  //   this.cdw = cdw;
  //   this.pricelist = pricelist;
  //   this.numberOfStars = numberOfStars;
  //   this.numberOfReviews = numberOfReviews;
  //   this.availableMileage = availableMileage;
  // }
}
