import { Component, OnInit, Input } from '@angular/core';
import { DateTime } from "luxon";
import { Review } from 'src/app/models/Review.model';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  @Input() approved: boolean = true; // Might be used when showing reviews with additional info to administrator 

  @Input() review: Review;
  formatedDate: any;

  constructor() { 
    if(!this.review){
      this.review = new Review(4,1588712400000,{id:2,name:"Petar Basic"},
      {id:4,name: "Bla bla"},
      "Curabitur nulla dui, condimentum placerat dolor a, cursus hendrerit nisl."+
      "Vestibulum venenatis ac diam vel accumsan." +
      "Nulla nec accumsan felis, faucibus feugiat libero.");
    }
  }

  ngOnInit() {
    this.formatedDate = DateTime.fromMillis(this.review.date).toLocaleString();
  }

}
