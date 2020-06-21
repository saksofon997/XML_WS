import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class RoleService {

constructor(private cookieService: CookieService,
  // private userService: UserService,
  private http: HttpClient,
  private router: Router) { }

  getAll() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.get(`${API_URL}/role`, { headers, observe: 'response' })
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
