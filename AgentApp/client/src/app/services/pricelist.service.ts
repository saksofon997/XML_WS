import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Brand } from '../models/Brand.model';
import { Pricelist } from '../models/Pricelist.model';

const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class PricelistService {

  constructor(private cookieService: CookieService,
    // private userService: UserService,
    private http: HttpClient,
    private router: Router) { }

  getByOwner(ownerID: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.get(`${API_URL}/pricelist/owner/${ownerID}`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  getOne(pricelistID: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get(`${API_URL}/pricelist/${pricelistID}`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  add(pricelist: Pricelist) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post(`${API_URL}/pricelist`, pricelist, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  edit(pricelistId: number, pricelist: Pricelist) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
      });
    return this.http.put(`${API_URL}/pricelist/${pricelistId}`, pricelist, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  delete(pricelistId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.delete(`${API_URL}/pricelist/${pricelistId}`, { headers, observe: 'response' })
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