import { Car } from './Car.model';
import { Bundle } from './Bundle.model';

export class RentalFront {
  id: number;
  car: Car;
  from: number;
  to: number;
  bundle: string = null;
}

export class RentalBack {
  id: number;
  vehicleId: number;
  customerId: number;
  ownerId: number;
  startTime: Date;
  endTime: Date;
  bundle: Bundle = null;
}