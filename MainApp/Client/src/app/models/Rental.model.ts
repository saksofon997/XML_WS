import { Car } from './Car.model';
import { Bundle } from './Bundle.model';
import { RentalReport } from './RentalReport.model';

export class RentalFront {
  id: number;
  car: Car;
  from: number;
  to: number;
  bundle: Bundle = null;
  report: RentalReport;

  constructor(id: number, car: Car, from: number, to: number, bundle: Bundle, report: RentalReport) {
    this.id = id;
    this.car = car;
    this.from = from;
    this.to = to;
    this.bundle = bundle;
    this.report = report;
  }
}

export class RentalBack {
  id: number;
  vehicleId: number;
  customerId: number;
  ownerId: number;
  startTime: number;
  endTime: number;
  bundle: Bundle = null;
  report: RentalReport;

  constructor(id: number, vehicleId: number, customerId: number, ownerId: number,
    startTime: number, endTime: number, bundle: Bundle, report: RentalReport) {
    this.id = id;
    this.vehicleId = vehicleId;
    this.customerId = customerId;
    this.ownerId = ownerId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.bundle = bundle;
    this.report = report;
  }
}