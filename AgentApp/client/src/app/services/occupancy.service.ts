import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { VehicleOccupancy } from '../models/VehicleOccupancy.model';

const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private cookieService: CookieService,
    // private userService: UserService,
    private http: HttpClient,
    private router: Router) { }

  getByVehicle(vehicleId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.get(`${API_URL}/vehicle/${vehicleId}/occupancy`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  getOne(vehicleId: number, startTime: number, endTime: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get(`${API_URL}/vehicle/${vehicleId}/occupancy/start/${startTime}/end/${endTime}`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  add(vehicleId: number, occupancy: VehicleOccupancy) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post(`${API_URL}/vehicle/${vehicleId}/occupancy`, occupancy, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  edit(vehicleId: number, occupancyId: number, occupancy: VehicleOccupancy) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.put(`${API_URL}/vehicle/${vehicleId}/occupancy/${occupancyId}`, occupancy, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error.message);
        })
      );
  }

  delete(vehicleId: number, occupancyId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.delete(`${API_URL}/vehicle/${vehicleId}/occupancy/${occupancyId}`, { headers, observe: 'response' })
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
