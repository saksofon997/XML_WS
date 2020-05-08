import { Component, OnInit, Input } from '@angular/core';
import { DateTime } from "luxon";

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  @Input() approved: boolean = true; // Might be used when showing reviews with additional info to administrator 

  @Input() review: any = {
    stars: 4,
    date: 1588712400000,
    user : {
      id: 2,
      name: "Mihajlo Perendija"
    },
    vehicle: {
      id: 4,
      name: "Bla bla"
    },
    text: "Curabitur nulla dui, condimentum placerat dolor a, cursus hendrerit nisl."+
     "Vestibulum venenatis ac diam vel accumsan." +
     "Nulla nec accumsan felis, faucibus feugiat libero."
  } 

  formatedDate: any;

  constructor() { }

  ngOnInit() {
    this.formatedDate = DateTime.fromMillis(this.review.date).toLocaleString();
  }

}
