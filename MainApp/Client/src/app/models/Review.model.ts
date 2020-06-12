
export class Review {
  stars: number;
  date: number;
  user: {
    id: number,
    name: string
  };
  vehicle: {
    id: string,
    name: string
  };
  text: string;

  constructor(stars: number, date: number, user: any, vehicle: any, text: string) {
    this.stars = stars;
    this.date = date;
    this.user = user;
    this.vehicle = vehicle;
    this.text = text;
  }

}
