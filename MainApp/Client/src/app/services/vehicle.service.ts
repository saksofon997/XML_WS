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
export class VehicleService {

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
    return this.http.get(`${API_URL}/vehicle`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }

  getByOwner(ownerID: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'pageable': `true`
    });
    return this.http.get(`${API_URL}/owner/${ownerID}/vehicle`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }
  getOne(vehicleID) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get(`${API_URL}/vehicle/${vehicleID}`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }

	addVehicle(vehicle, images) {
		let headers = new HttpHeaders({
			"x-auth":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctc2VjdXJpdHktZXhhbXBsZSIsInN1YiI6ImFkbWluQGFkbWluLnJzIiwiYXVkIjoid2ViIiwiaWF0IjoxNTkxMzAwNzc5LCJhdXRob3JpdGllcyI6WyJBQ1RJVkFURV9VU0VSX1BFUk1JU1NJT04iLCJDSEFOR0VfUkVOVEFMX1BFUk1JU1NJT04iLCJDSEFOR0VfVVNFUl9QRVJNSVNTSU9OIiwiQ1JFQVRFX1JFTlRBTF9QRVJNSVNTSU9OIiwiQ1JFQVRFX1VTRVJfUEVSTUlTU0lPTiIsIkRFQUNUSVZBVEVfVVNFUl9QRVJNSVNTSU9OIiwiREVMRVRFX1JFTlRBTF9QRVJNSVNTSU9OIiwiUkVNT1ZFX1VTRVJfUEVSTUlTU0lPTiIsIlJPTEVfQURNSU4iLCJST0xFX1NJTVBMRV9VU0VSIiwiUk9MRV9WRUhJQ0xFX09XTkVSIl0sInVzZXJJZCI6MSwiaXNBZ2VudCI6ZmFsc2V9.Bm8TwVK-QlImN21vhyf2YCcefCRhs6Q_BXUyNG1R7LDSCH-9-4XsuU65PtJyLHgHsi0KQXYHc-5gHCWhiT0k7g"
		});
		
		let formData=new FormData();
		for (const i in images) {
			formData.append("images", images[i]);
			console.log(images[i]);
		}
		formData.append('vehicle', new Blob([JSON.stringify(vehicle)],
        {
            type: "application/json"
		}));
		return this.http.post(`${API_URL}/vehicle`, formData, {headers, observe: 'response' }).pipe(
			map(response => {
				return response.body;
			}),
			catchError((response) => {
				return throwError(response.error);
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
          return throwError(response.error);
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
          return throwError(response.error);
        })
      );
  }

}
