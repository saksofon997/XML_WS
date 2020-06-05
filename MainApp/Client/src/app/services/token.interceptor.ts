import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor
} from '@angular/common/http';
import { UserService } from './user.service';
import { Observable } from 'rxjs';

// temp
const adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctc2VjdXJpdHktZXhhbXBsZSIsInN1YiI6ImFkbWluQGFkbWluLnJzIiwiYXVkIjoid2ViIiwiaWF0IjoxNTkxMzA5NTk4LCJhdXRob3JpdGllcyI6WyJBQ1RJVkFURV9VU0VSX1BFUk1JU1NJT04iLCJDSEFOR0VfUkVOVEFMX1BFUk1JU1NJT04iLCJDSEFOR0VfVVNFUl9QRVJNSVNTSU9OIiwiQ1JFQVRFX1JFTlRBTF9QRVJNSVNTSU9OIiwiQ1JFQVRFX1VTRVJfUEVSTUlTU0lPTiIsIkRFQUNUSVZBVEVfVVNFUl9QRVJNSVNTSU9OIiwiREVMRVRFX1JFTlRBTF9QRVJNSVNTSU9OIiwiUkVNT1ZFX1VTRVJfUEVSTUlTU0lPTiIsIlJPTEVfQURNSU4iLCJST0xFX1NJTVBMRV9VU0VSIiwiUk9MRV9WRUhJQ0xFX09XTkVSIl0sInVzZXJJZCI6MSwiaXNBZ2VudCI6ZmFsc2V9.UHiILCrOygnoIOw21Jb-esKYWYoU3KXHdC3q33Y5Q7MbxW8JzHmy-YxeMHzlGOeuEWwLRS_JwK9EanoZE7XgEQ"

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    constructor(public userService: UserService) { }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const login = /login/gi;
        const yandex = /apikey/gi; 
        const register = /register/gi;
        if (request.url.search(login) === -1 && request.url.search(yandex) === -1 && request.url.search(register) === -1) {
            request = request.clone({
                setHeaders: {
                    'x-auth': `Bearer ${adminToken}`  //`Bearer ${this.userService.getToken()}`
                }
            });
        }
        return next.handle(request);
    }
}