import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {AuthenticationService} from './authentication-service';
import {User} from '../models/user.model';


@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const username = localStorage.getItem('username')
    const password = localStorage.getItem('password')

    if (username && password) {
      request = request.clone({
        setHeaders: {
          username: `${username}`,
          password: ` ${password}`
        }
      });
    }

    return next.handle(request);
  }
}
