import { Component, OnInit, Input, SimpleChanges } from '@angular/core';
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
  }

  ngOnInit() {
    this.formatedDate = DateTime.fromMillis(this.review.date * 1000).toLocaleString();
  }

}
