import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Review } from '../models/Review.model';

const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private cookieService: CookieService,
    // private userService: UserService,
    private http: HttpClient,
    private router: Router) { }


  getByVehicle(vehicleId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.get(`${API_URL}/vehicle/${vehicleId}/review`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  getPageablePending(pageNo: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'pageable': `true`,
      'page': pageNo.toString()
    });
    return this.http.get(`${API_URL}/review`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  getAllPending() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'pageable': `false`,
    });
    return this.http.get(`${API_URL}/review`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  getOne(vehicleId: number, reviewId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get(`${API_URL}/vehicle/${vehicleId}/review/${reviewId}`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  add(vehicleId: number, review: Review) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post(`${API_URL}/vehicle/${vehicleId}/review`, review, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  edit(vehicleId: number, reviewId: number, review: Review) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.put(`${API_URL}/vehicle/${vehicleId}/review/${reviewId}`, review, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  delete(vehicleId: number, reviewId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.delete(`${API_URL}/vehicle/${vehicleId}/review/${reviewId}`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

}
