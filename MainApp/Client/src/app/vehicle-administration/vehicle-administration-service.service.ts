import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs/internal/observable/throwError';

@Injectable({
  providedIn: 'root'
})
export class VehicleAdministrationServiceService {

constructor(private http: HttpClient,
  private router: Router,
  private userService: UserService,) { }

	addVehicle(vehicle, images) {
		let headers = new HttpHeaders({
			"x-auth":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctc2VjdXJpdHktZXhhbXBsZSIsInN1YiI6ImFkbWluQGFkbWluLnJzIiwiYXVkIjoid2ViIiwiaWF0IjoxNTkwNzUxNzc0LCJhdXRob3JpdGllcyI6WyJSRUFEX1BFUk1JU1NJT04iLCJST0xFX0FETUlOIiwiV1JJVEVfUEVSTUlTU0lPTiJdfQ.Hq03SjCYSQlgFGnnM9jSg-Ogo42GPDcODqrgHYiPQDMeM0ppthU4tqZY2EHIJBIV6AxA02tkkvfR8u_SAcbM9Q"
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
		return this.http.post(`http://localhost:8080/vehicle`, formData, {observe: 'response' }).pipe(
			map(response => {
				return response.body;
			}),
			catchError((response) => {
				return throwError(response.error);
			})
		);
	}


}
