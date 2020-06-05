import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { Fuel } from '../models/Fuel.model';

const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class FuelService {

  constructor(private cookieService: CookieService,
    // private userService: UserService,
    private http: HttpClient,
    private router: Router) { }

  getPageable(pageNo: Number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'pageable': `true`,
      'page': pageNo.toString()
    });
    return this.http.get(`${API_URL}/fuel`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }

  getAll() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'pageable': `false`,
    });
    return this.http.get(`${API_URL}/fuel`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }

  add(fuel: Fuel) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post(`${API_URL}/fuel`, fuel, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }

  edit(fuelId: number, fuel: Fuel) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.put(`${API_URL}/fuel/${fuelId}`, fuel, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }

  delete(fuelId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.delete(`${API_URL}/fuel/${fuelId}`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }

}
