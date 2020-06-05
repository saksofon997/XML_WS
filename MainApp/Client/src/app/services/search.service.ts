import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

import { environment } from '../../environments/environment';

const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private cookieService: CookieService,
    // private userService: UserService,
    private http: HttpClient,
    private router: Router) { }

  search(pageNo: number, searchParams) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      page: pageNo.toString()
    });
    let searchParamsString = '';
    for (var key in searchParams) {
      if (searchParams.hasOwnProperty(key)) {
        searchParamsString += `${key}=${searchParams[key]}&`
      }
    }

    return this.http.get(`${API_URL}/search?${searchParamsString}`, { headers, observe: 'response' })
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
