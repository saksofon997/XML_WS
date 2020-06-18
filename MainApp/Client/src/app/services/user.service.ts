import { Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { interval, throwError, Observable } from 'rxjs';
import { flatMap, map, catchError } from 'rxjs/operators';

import { environment } from 'src/environments/environment';

const API_URL = environment.API_URL;


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private user: any = null;
  private token: any = null;
  passwordChanged = true;
  private get router() { return this.injector.get(Router); }

  constructor(private cookieService: CookieService,
    private http: HttpClient,
    private injector: Injector) {
    interval(120000)
      .pipe(
        flatMap(() => this.checkLoggedIn())
      ).subscribe(() => { });
  }

  /*
	*  Check if there is a logged in user
	*/
  checkLoggedIn() {
    if (this.cookieService.get('user')) {
      this.user = JSON.parse(this.cookieService.get('user'));
      this.token = JSON.parse(this.cookieService.get('token'));
    }
    return this.user;
  }
  /*
	*  Stores user in memory and cookieservice
	*/
  setUser(user: any) {
    this.user = user;
    this.cookieService.set('user', JSON.stringify(user));
  }

  setToken(token: any) {
    this.token = token;
    this.cookieService.set('token', JSON.stringify(token));
  }

  getToken() {
    return JSON.parse(this.cookieService.get('token'));
  }

  getUser() {
    return JSON.parse(this.cookieService.get('user'));
  }

  /*
	*  Removes user from memory and cookieservice
	*/
  removeUser() {
    this.user = null;
    this.cookieService.delete('user');
  }

  removeToken() {
    this.token = null;
    this.cookieService.delete('token');
  }

  tokenIsPresent() {
    return this.token != undefined && this.token != null;
  }
  /*
	*  Sends http request to server
	*  Calls setUser to store it in memory and cookieservice
	*  Prints error to console
	*/
  login(email: string, password: string) {
    const user = {
      email,
      password
    }
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post(`${API_URL}/auth/login`, user, { headers, observe: 'response' })
      .pipe(
        map((response) => {
          const userState = response.body;
          this.setUser(userState['user']);
          this.setToken(userState['token']);
          this.router.navigate(['/rentals']);
          return this.user;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }

  register(registerRequest: any) {
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    return this.http.post(`${API_URL}/auth/signup`, registerRequest, { headers, observe: 'response' })
      .pipe(
        map((response) => {
          const userState = response.body;
          alert("Your request has been sent. Check your email.")
          this.router.navigate(['/login']);

          return this.user;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  logout() {
    this.removeUser();
    this.removeToken();
    this.router.navigate(['/login']);
  }
}
