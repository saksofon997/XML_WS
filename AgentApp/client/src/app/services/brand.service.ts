import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

import { Brand } from 'src/app/models/Brand.model';
import { environment } from '../../environments/environment';

const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class BrandService {

  constructor(private cookieService: CookieService,
    // private userService: UserService,
    private http: HttpClient,
    private router: Router) { }

  getPageable(pageNo: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'pageable': `true`,
      'page': pageNo.toString()
    });
    return this.http.get(`${API_URL}/brand`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  getAll() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'pageable': `false`,
    });
    return this.http.get(`${API_URL}/brand`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  add(brand: Brand) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post(`${API_URL}/brand`, brand, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  edit(brandId: number, brand: Brand) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.put(`${API_URL}/brand/${brandId}`, brand, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  delete(brandId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.delete(`${API_URL}/brand/${brandId}`, { headers, observe: 'response' })
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
