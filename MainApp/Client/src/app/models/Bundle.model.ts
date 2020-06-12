import { RentalFront } from './Rental.model';

export class Bundle {
  name: string;
  rentals?: Array<RentalFront>;
  owner: number;

  constructor(name: string, rentals: Array<RentalFront>, owner: number) {
    this.name = name;
    this.rentals = rentals;
    this.owner = owner;
  }
}