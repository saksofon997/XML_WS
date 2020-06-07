import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

import { environment } from '../../environments/environment';
import { Category } from '../models/Category.model';

const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

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
    return this.http.get(`${API_URL}/category`, { headers, observe: 'response' })
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
    return this.http.get(`${API_URL}/category`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  add(category: Category) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post(`${API_URL}/category`, category, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  edit(categoryId: number, category: Category) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.put(`${API_URL}/category/${categoryId}`, category, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  delete(categoryId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.delete(`${API_URL}/category/${categoryId}`, { headers, observe: 'response' })
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
