import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { RentalReport } from '../models/RentalReport.model';

const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class RentalReportService {

constructor(private http: HttpClient) { }


add(report: RentalReport) {
  let headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });
  return this.http.post(`${API_URL}/rental/${report.rentalId}/rental_report`, report, { headers, observe: 'response' })
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
