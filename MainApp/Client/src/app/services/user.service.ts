import { Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { interval, throwError, Observable } from 'rxjs';
import { flatMap, map, catchError } from 'rxjs/operators';

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
		interval(200000)
			.pipe(
				flatMap(() => this.refreshToken())
			).subscribe(() => { })
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
		return JSON.parse(this.cookieService.get('token')).accessToken;
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
		var user = {
			email,
			password
		}
		const headers = new HttpHeaders({
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		});
		return this.http.post('/auth/login', user, { headers, observe: 'response' })
			.pipe(
				map((response) => {
					const userState = response.body;
					this.setUser(userState['user']);
					this.setToken(userState['token']);
					this.router.navigate(['/']);
					return this.user;
				}),
				catchError((response) => {
					return throwError(response.error.message);
				})
			);
	}

	register(registerRequest: any) {
		const headers = new HttpHeaders({
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		});
		return this.http.post('/auth/signup', registerRequest, { headers, observe: 'response' })
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

	changePassowrd(passwordChanger) {
		const headers = new HttpHeaders({
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'x-auth': `Bearer ${this.getToken()}`
		});
		return this.http.post('/auth/change-password', JSON.stringify(passwordChanger), { headers, observe: 'response' })
			.pipe(
				map((response) => {
					this.passwordChanged = true;
				}),
				catchError((response) => {
					return throwError(response.error.message);
				})
			);
	}

	refreshToken() {
		if (!(this.cookieService.get('token') === '')) {
			const headers = new HttpHeaders({
				'x-auth': `Bearer ${this.getToken()}`
			});
			return this.http.post('/auth/refresh', {}, { headers, observe: 'response' })
				.pipe(
					map((response) => {
						const userState = response.body;
						this.setUser(userState['user']);
						this.setToken(userState['token']);
						this.passwordChanged = userState['passwordChanged'];
						if (!this.passwordChanged) {
							this.router.navigate(['/change-password']);
						}
						return this.user;
					}),
					catchError((response) => {
						this.removeUser();
						this.removeToken();
						this.router.navigate(['/login']);
						return throwError(response.error.message);
					})
				);
		} else {
			this.removeUser();
			this.removeToken();
			return new Observable();
		}
	}
}
