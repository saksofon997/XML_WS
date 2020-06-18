import { Car } from './Car.model';

export class Review {
  id: number;
  customerId: number;
  customerName: string;
  vehicle: Car;
  stars: number;
  text: string;
  date: number;

  // constructor(id: number, customerId: number, customerName: string, vehicle: Car, stars: number, text: string, date: number) {
  //   this.id = id;
  //   this.customerId = customerId;
  //   this.customerName = customerName;
  //   this.vehicle = vehicle;
  //   this.stars = stars;
  //   this.text = text;
  //   this.date = date;
  // }

}
