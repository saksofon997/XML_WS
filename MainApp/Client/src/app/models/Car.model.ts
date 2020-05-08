

export class Car {
    src : String;
    brand : String;
    model : String;
    fuel : String;
    transmission : String;
    carClass : String;
    seats: Number;
    mileage: String;
    price: Number;
    commentNo: Number;

    constructor(src: String, brand: String, model: String, fuel: String,
        transmission : String, carClass : String, seats: Number, mileage: String,
         price: Number, commentNo: Number){
             this.src = src;
             this.brand = brand;
             this.model = model;
             this.fuel = fuel;
              this.transmission = transmission;
              this.carClass = carClass;
              this.seats = seats;
              this.mileage = mileage;
              this.price = price;
              this.commentNo = commentNo;

    }

    
}
