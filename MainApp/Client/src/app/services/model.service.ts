import { Injectable } from '@angular/core';
import { Model } from 'src/app/models/Model.model';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

const adminToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctc2VjdXJpdHktZXhhbXBsZSIsInN1YiI6ImFkbWluQGFkbWluLnJzIiwiYXVkIjoid2ViIiwiaWF0IjoxNTkxMTk3MTMxLCJhdXRob3JpdGllcyI6WyJBQ1RJVkFURV9VU0VSX1BFUk1JU1NJT04iLCJDSEFOR0VfUkVOVEFMX1BFUk1JU1NJT04iLCJDSEFOR0VfVVNFUl9QRVJNSVNTSU9OIiwiQ1JFQVRFX1JFTlRBTF9QRVJNSVNTSU9OIiwiQ1JFQVRFX1VTRVJfUEVSTUlTU0lPTiIsIkRFQUNUSVZBVEVfVVNFUl9QRVJNSVNTSU9OIiwiREVMRVRFX1JFTlRBTF9QRVJNSVNTSU9OIiwiUkVNT1ZFX1VTRVJfUEVSTUlTU0lPTiIsIlJPTEVfQURNSU4iLCJST0xFX1NJTVBMRV9VU0VSIiwiUk9MRV9WRUhJQ0xFX09XTkVSIl0sInVzZXJJZCI6MX0.kyY0UCau-M05ECexORgaOrMDxMed7QI4xa5tggXX_sMjX5kJGjI_wPowPy7yCXH_aahgql0J-EudrlspgJ0sEw"
const API_URL = environment.API_URL;

@Injectable({
  providedIn: 'root'
})
export class ModelService {

  constructor(private cookieService: CookieService,
    // private userService: UserService,
    private http: HttpClient,
    private router: Router) { }

  getPageable(brandId: any, pageNo: Number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'pageable': `true`,
      'page': pageNo.toString()
    });
    return this.http.get(`${API_URL}/brand/${brandId}/model`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }

  getAll(brandId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'pageable': `false`,
    });
    return this.http.get(`${API_URL}/brand/${brandId}/model`, { headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }

  get(brandId: number) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.get(`/brand/${brandId}/model`, { headers: headers, observe: 'response' })
      .pipe(
        map(response => {
          return response.body;
        }),
        catchError((response) => {
          return throwError(response.error);
        })
      );
  }

  add(brandId: number, model: Model) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post(`/brand/${brandId}/model`, model, { headers: headers, observe: 'response' }).pipe(
      map(response => {
        return response.body;
      }),
      catchError((response) => {
        return throwError(response.error);
      })
    );
  }

  edit(brandId: number, modelId: number, model: Model) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.put(`/brand/${brandId}/model/${modelId}`, model, { headers: headers, observe: 'response' }).pipe(
      map(response => {
        return response.body;
      }),
      catchError((response) => {
        return throwError(response.error);
      })
    );
  }

  delete(brandId: number, modelId: any) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.delete(`/brand/${brandId}/model/${modelId}`, { headers: headers, observe: 'response' }).pipe(
      map(response => {
        return response.body;
      }),
      catchError((response) => {
        return throwError(response.error);
      })
    );
  }

}
