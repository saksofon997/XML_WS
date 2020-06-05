import { Car } from './Car.model';
import { Bundle } from './Bundle.model';

export class RentalFront {
  id: number;
  car: Car;
  from: number;
  to: number;
  bundle: Bundle = null;
}

export class RentalBack {
  id: number;
  vehicleId: number;
  customerId: number;
  ownerId: number;
  startTime: number;
  endTime: number;
  bundle: Bundle = null;
}