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
const adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctc2VjdXJpdHktZXhhbXBsZSIsInN1YiI6ImFkbWluQGFkbWluLnJzIiwiYXVkIjoid2ViIiwiaWF0IjoxNTkxMzA5NTk4LCJhdXRob3JpdGllcyI6WyJBQ1RJVkFURV9VU0VSX1BFUk1JU1NJT04iLCJDSEFOR0VfUkVOVEFMX1BFUk1JU1NJT04iLCJDSEFOR0VfVVNFUl9QRVJNSVNTSU9OIiwiQ1JFQVRFX1JFTlRBTF9QRVJNSVNTSU9OIiwiQ1JFQVRFX1VTRVJfUEVSTUlTU0lPTiIsIkRFQUNUSVZBVEVfVVNFUl9QRVJNSVNTSU9OIiwiREVMRVRFX1JFTlRBTF9QRVJNSVNTSU9OIiwiUkVNT1ZFX1VTRVJfUEVSTUlTU0lPTiIsIlJPTEVfQURNSU4iLCJST0xFX1NJTVBMRV9VU0VSIiwiUk9MRV9WRUhJQ0xFX09XTkVSIl0sInVzZXJJZCI6MSwiaXNBZ2VudCI6ZmFsc2V9.UHiILCrOygnoIOw21Jb-esKYWYoU3KXHdC3q33Y5Q7MbxW8JzHmy-YxeMHzlGOeuEWwLRS_JwK9EanoZE7XgEQ"

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
      'Content-Type': 'application/json',
      'x-auth': `Bearer ${adminToken}`
    });
    return this.http.post(`${API_URL}/checkout`, rentals , { headers, observe: 'response', responseType: 'text' })
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
