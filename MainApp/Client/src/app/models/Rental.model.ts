import { Car } from './Car.model';

export class Rental {
  car: Car;
  from: Date;
  to: Date;
  constructor(car: Car, from: Date, to: Date){
    this.car = car;
    this.from = from;
    this.to = to;
  }
}