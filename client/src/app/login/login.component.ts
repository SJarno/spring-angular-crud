import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credentials = {"username": "", "password": ""};

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }
  login() {
    this.auth.authenticate(this.credentials, (err?:any) => {
      if (err) {
        if (err.status == 401) {
          console.error("Auth failed");
        } else {
          console.error(err.status);
        }
      } else {
        this.router.navigateByUrl("/todos");
      }
      
    });
    return false;
  }

}
