import { RentalFront } from './Rental.model';
import { Bundle } from './Bundle.model';

export class ShoppingCart {
    rentals: Array<RentalFront>;
    bundles: Array<Bundle>;

    constructor(rentals: Array<RentalFront>, bundles: Array<Bundle>) {
        this.rentals = rentals;
        this.bundles = bundles;
    }
}