import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

import { Brand } from 'src/app/models/Brand.model';
import { environment } from '../../environments/environment';
import { RentalBack } from '../models/Rental.model';

const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class RentalService {

  constructor(private cookieService: CookieService,
    // private userService: UserService,
              private http: HttpClient,
              private router: Router) { }

  checkout(rentals: Array<RentalBack>) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post(`${API_URL}/checkout`, rentals, { headers, observe: 'response', responseType: 'text' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  getCustomerRentals(customerId: number, status: string, pageNo: number) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'pageable': `true`,
      'page': pageNo.toString()
    });
    return this.http.get(`${API_URL}/customer/${customerId}/rental/status/${status}`,
      { headers, observe: 'response' })
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
