
export class Review {
    stars: Number;
    date: Number;
    user : {
      id: Number,
      name: String
    };
    vehicle: {
      id: String,
      name:String
    };
    text: String;

	constructor(stars: Number, date: Number,user: any,vehicle: any,text: String ) {
        this.stars = stars;
        this.date = date;
        this.user = user;
        this.vehicle = vehicle;
        this. text = text;
	}

}
