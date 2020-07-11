import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
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
import { RentalService } from 'src/app/services/rental.service';

@Component({
  selector: 'app-rental-teaser',
  templateUrl: './rental-teaser.component.html',
  styleUrls: ['./rental-teaser.component.css']
})
export class RentalTeaserComponent implements OnInit {

  @Input() rental: RentalFront;
  @Input() status: string;
  @Input() customer: boolean;
  @Output() delete: EventEmitter<RentalFront> = new EventEmitter();

  constructor(private userService: UserService,
    private rentalReportService: RentalReportService,
    private reviewService: ReviewService,
    private rentalService: RentalService,
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

      const car = new Car();
      car.id = this.rental.car.id;

      //let car = new Car(this.rental.car.id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

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

    const rental = new RentalBack(this.rental.id, this.rental.car.id, this.rental.customerId,
      this.rental.ownerId, this.rental.from, this.rental.to, null, null, 'RESERVED', this.rental.company);
    this.rentalService.edit(this.rental.id, rental).subscribe(
      (data: any) => {
        this.delete.emit(this.rental);
      },
      (error) => {
        alert(error.message);
        if (error.status === 'CONFLICT') {
          this.delete.emit(this.rental);
        }
      }
    );
  }

  rejectRental($event) {
    $event.stopPropagation();

    const rental = new RentalBack(this.rental.id, this.rental.car.id, this.rental.customerId,
      this.rental.ownerId, this.rental.from, this.rental.to, null, null, 'CANCELED', this.rental.company);

    this.rentalService.edit(this.rental.id, rental).subscribe(
      (data: any) => {
        this.delete.emit(this.rental);
      },
      (error) => {
        alert(error.message);
      }
    );
  }

  finishRental($event) {
    $event.stopPropagation();

    const rental = new RentalBack(this.rental.id, this.rental.car.id, this.rental.customerId,
      this.rental.ownerId, this.rental.from, this.rental.to, null, null, 'FINISHED', this.rental.company);

    this.rentalService.edit(this.rental.id, rental).subscribe(
      (data: any) => {
        this.delete.emit(this.rental);
      },
      (error) => {
        alert(error.message);
      }
    );
  }

  newReport($event) {
    $event.stopPropagation();

    const dialogRef = this.dialog.open(NewRentalReportDialogboxComponent, {
      width: '500px',
      data: { report: new RentalReport(), rental: this.rental }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event === 'Cancel') {
        return;
      }
      const report = new RentalReport();
      report.description = result.data.description;
      report.mileage = result.data.mileage;
      report.rentalId = this.rental.id;

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
