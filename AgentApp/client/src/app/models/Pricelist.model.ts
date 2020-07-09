export class Pricelist {
  id: number;
  ownerId: number;
  name: string;
  pricePerDay: number;
  pricePerKm: number;
  cdw: boolean;
  description: string;
  discount: number;
  penalty: number;
}