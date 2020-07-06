import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor
} from '@angular/common/http';
import { UserService } from './user.service';
import { Observable } from 'rxjs';

// Left here just in case
export const token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctc2VjdXJpdHktZXhhbXBsZSIsInN1YiI6ImFkbWluQGFkbWluLnJzIiwiYXVkIjoid2ViIiwiaWF0IjoxNTkyMzI3MzQ2LCJhdXRob3JpdGllcyI6WyJBQ1RJVkFURV9VU0VSX1BFUk1JU1NJT04iLCJBUFBST1ZFX1JFVklFV19QRVJNSVNTSU9OIiwiQ0hBTkdFX1BSSUNFTElTVF9QRVJNSVNTSU9OIiwiQ0hBTkdFX1VTRVJfUEVSTUlTU0lPTiIsIkNIQU5HRV9WRUhJQ0xFX09DQ1VQQU5DWSIsIkNIQU5HRV9WRUhJQ0xFX1BBUlRfUEVSTUlTU0lPTiIsIkNSRUFURV9QUklDRUxJU1RfUEVSTUlTU0lPTiIsIkNSRUFURV9VU0VSX1BFUk1JU1NJT04iLCJDUkVBVEVfVkVISUNMRV9PQ0NVUEFOQ1kiLCJDUkVBVEVfVkVISUNMRV9QQVJUX1BFUk1JU1NJT04iLCJERUFDVElWQVRFX1VTRVJfUEVSTUlTU0lPTiIsIkRFTEVURV9QUklDRUxJU1RfUEVSTUlTU0lPTiIsIkRFTEVURV9WRUhJQ0xFX09DQ1VQQU5DWSIsIkRFTEVURV9WRUhJQ0xFX1BBUlRfUEVSTUlTU0lPTiIsIlJFTU9WRV9VU0VSX1BFUk1JU1NJT04iLCJST0xFX0FETUlOIiwiVklFV19QUklDRUxJU1RfUEVSTUlTU0lPTiJdLCJ1c2VySWQiOjEsImlzQWdlbnQiOmZhbHNlfQ.LycfSEYzMkzayie2B24qCgl6ZOqE9iIaOFrOMfrl7Kju8NBRciyGNvyls-YhkVeS9QqJ-tzTlSD0pO6QDmzMPw"

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    constructor(public userService: UserService) { }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const login = /login/gi;
        const yandex = /apikey/gi; 
        const register = /register/gi;
        const locate = /locate/gi;
        if (request.url.search(login) === -1 && request.url.search(yandex) === -1 && request.url.search(register) === -1 && request.url.search(locate) === -1) {
            request = request.clone({
                setHeaders: {
                    'x-auth': `Bearer ${this.userService.getToken()}` //${token}
                }
            });
        }
        return next.handle(request);
    }
}