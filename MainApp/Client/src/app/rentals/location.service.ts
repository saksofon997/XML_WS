import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators'
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

constructor(private http: HttpClient) { }

getPossiblePlaces(address: string) {
  address = address.replace(/ /g, '+');
  address = encodeURIComponent(address);

  return this.http.get(`https://geocode-maps.yandex.ru/1.x/?format=json&apikey=396fefe7-95c2-486a-ae3e-c8f062813962&geocode=${address}&lang=en_US`, { observe: 'response' })
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
