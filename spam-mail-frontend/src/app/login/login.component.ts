import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {APP_URL} from '../models/url.model';
import {ToastrService} from 'ngx-toastr';
import {AuthenticationService} from '../services/authentication-service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  username: string = '';
  password: string = '';
  readonly appUrl = APP_URL;

  constructor(private http: HttpClient, private toastr: ToastrService, private  authService: AuthenticationService, private router:Router) {
  }

  ngOnInit() {
  }

  login() {
    this.authService.login(this.username, this.password);

    this.http.get(this.appUrl + '/login').subscribe(
      res => {
        this.toastr.success('zalogowano')
        this.router.navigateByUrl("admin-panel")
      },
      err => this.toastr.error('błędny login lub hasło')
    );
  }

  signUp() {
    console.log(this.username);
    console.log(this.password);
    this.http.post(this.appUrl + '/signup', {
      username: this.username,
      password: this.password
    }).subscribe(
      _ => this.toastr.success('Użytkownik został założony', 'Sukces'),
      err => this.toastr.error('Cos sie popsuło i nie było mnie słychać')
    );
  }

}
