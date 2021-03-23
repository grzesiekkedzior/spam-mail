import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};


  constructor() {}

  ngOnInit() {
  }

  login() {
    console.log("IMPLEMENT ME")
  }

  signUp(){
    console.log("implement me aswel")
  }

}
