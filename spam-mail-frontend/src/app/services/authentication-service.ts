import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import {User} from '../models/user.model';
import {APP_URL} from '../models/url.model';


@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public username: Observable<string>;
  public password: Observable<string>;

  appUrl = APP_URL

  constructor(private http: HttpClient) {
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
        localStorage.setItem('username',username );
        localStorage.setItem('password',password );
  };

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
