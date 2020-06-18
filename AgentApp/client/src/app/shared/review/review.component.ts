import { Component, OnInit, Input } from '@angular/core';
import { DateTime } from "luxon";
import { Review } from 'src/app/models/Review.model';
import { CookieService } from 'ngx-cookie-service';

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
    if(!this.review) {
    this.review = new Review(1, 2, "Test", null, 4, "text: string", 1592233200);
    }
    console.log(this.review);
  }

  ngOnInit() {
    this.formatedDate = DateTime.fromMillis(this.review.date).toLocaleString();
  }

}
