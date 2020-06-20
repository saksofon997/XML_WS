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

}
