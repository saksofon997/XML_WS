import { Injectable, Injector } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  constructor(private cookieService: CookieService,
    private http: HttpClient,
    private injector: Injector) { }

  send(address: string, message: string) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Access-Control-Allow-Origin': '*'
    });

    let body = {
      "address": address,
      "message": message
    }

    return this.http.post(`https://hsejfoit96.execute-api.us-east-1.amazonaws.com/dev/email`, body, { headers, observe: 'response' })
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
