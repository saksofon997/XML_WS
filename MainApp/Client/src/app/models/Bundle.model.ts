import { RentalFront } from './Rental.model';

export class Bundle {
  name: string;
  rentals?: Array<RentalFront>;
  owner: Number;
}