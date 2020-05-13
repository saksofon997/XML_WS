import { Car } from './Car.model';

export class Rental {
  id: number;
  car: Car;
  from: Date;
  to: Date;
  bundle: string = null;
  constructor(id: number, car: Car, from: Date, to: Date){
    this.id = id;
    this.car = car;
    this.from = from;
    this.to = to;
  }
}
