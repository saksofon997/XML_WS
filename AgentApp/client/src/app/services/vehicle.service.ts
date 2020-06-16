import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

import { Brand } from 'src/app/models/Brand.model';
import { environment } from '../../environments/environment';
import { Car } from '../models/Car.model';

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
          return throwError(response.error.message);
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
          return throwError(response.error.message);
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
          return throwError(response.error.message);
        })
      );
  }

  addVehicle(vehicle, images) {
    let headers = new HttpHeaders({});

    let formData = new FormData();
    for (const i in images) {
      formData.append("images", images[i]);
      console.log(images[i]);
    }
    formData.append('vehicle', new Blob([JSON.stringify(vehicle)],
      {
        type: "application/json"
      }));
    return this.http.post(`${API_URL}/vehicle`, formData, { headers, observe: 'response' }).pipe(
      map(response => {
        return response.body;
      }),
      catchError((response) => {
        return throwError(response.error.message);
      })
    );
  }

  edit(vehicleId: number, vehicle: Car) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.put(`${API_URL}/vehicle/${vehicleId}`, vehicle, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  delete(vehicleId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.delete(`${API_URL}/vehicle/${vehicleId}`, { headers, observe: 'response' })
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
