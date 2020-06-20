import { Component, OnInit, Input } from '@angular/core';
import { RentalFront, RentalBack } from 'src/app/models/Rental.model';
import { CookieService } from 'ngx-cookie-service';
import { Review } from 'src/app/models/Review.model';
import { NewReviewDialogboxComponent } from '../new-review-dialogbox/new-review-dialogbox.component';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';
import { Car } from 'src/app/models/Car.model';
import { ReviewService } from 'src/app/services/review.service';
import { NewRentalReportDialogboxComponent } from '../new-rental-report-dialogbox/new-rental-report-dialogbox.component';
import { RentalReport } from 'src/app/models/RentalReport.model';
import { RentalReportService } from 'src/app/services/rental-report.service';

@Component({
  selector: 'app-rental-teaser',
  templateUrl: './rental-teaser.component.html',
  styleUrls: ['./rental-teaser.component.css']
})
export class RentalTeaserComponent implements OnInit {

  @Input() rental: RentalFront;
  @Input() status: string;
  @Input() customer: boolean;

  constructor(private userService: UserService,
    private rentalReportService: RentalReportService,
    private reviewService: ReviewService,
    public dialog: MatDialog,) { }

  ngOnInit() {
  }


  fromLocaleString() {
    const date = new Date(this.rental.from * 1000);
    return date.toLocaleString();
  }

  toLocaleString() {
    const date = new Date(this.rental.to * 1000);
    return date.toLocaleString();
  }

  sendMessage($event) {
    $event.stopPropagation();
    // TODO
  }

  newReview($event) {
    $event.stopPropagation();
    const dialogRef = this.dialog.open(NewReviewDialogboxComponent, {
      width: '500px',
      data: new Review()
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event === 'Cancel') {
        return;
      }
      const review = new Review();
      review.text = result.data.text;
      review.stars = result.data.stars;
      let car = new Car(this.rental.car.id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
      review.vehicle = car;
      review.customerId = this.userService.getUser().id;
      review.customerName = this.userService.getUser().name;
      review.date = Date.now() / 1000;

      console.log(review);
      this.reviewService.add(review.vehicle.id, review).subscribe(
        (data: any) => {

        },
        (error) => {
          alert(error);
        }
      );
    });
  }

  approveRental($event) {
    $event.stopPropagation();
    // TODO
  }

  rejectRental($event) {
    $event.stopPropagation();
    // TODO
  }

  newReport($event) {
    $event.stopPropagation();

    const dialogRef = this.dialog.open(NewRentalReportDialogboxComponent, {
      width: '500px',
      data: new RentalReport()
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event === 'Cancel') {
        return;
      }
      const report = new RentalReport();
      report.description = result.data.description;
      report.mileage = result.data.mileage;
      report.rentalId = this.rental.id;

      console.log(report);
      this.rentalReportService.add(report).subscribe(
        (data: any) => {
          this.rental.report = new RentalReport();
        },
        (error) => {
          alert(error);
        }
      );
    });
  }
}
