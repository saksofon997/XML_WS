export class Pricelist {
  id: number;
  ownerId: number;
  name: string;
  pricePerDay: number;
  pricePerKm: number;
  cdw: number;
  description: string;

  constructor(id: number, ownerId: number, name: string, pricePerDay: number,
    pricePerKm: number, cdw: number, description: string) {
    this.id = id;
    this.ownerId = ownerId;
    this.name = name;
    this.pricePerDay = pricePerDay;
    this.pricePerKm = pricePerKm;
    this.cdw = cdw;
    this.description = description;
  }
}