import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { finalize } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  username?: string;
  authenticated: boolean = false;
  authorities: any = [];

  constructor(private http: HttpClient, private router: Router) { }

  authenticate(credentials: any, callback: any) {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});
    this.http.get(environment.baseUrl + "user", { headers: headers }).subscribe((response) => {
      let result: any = response;
      console.log("Tulos: ");
      console.log(result);
      if (result['name']) {
        this.username = result['name'];

        this.authenticated = true;
        this.authorities = result['authorities'];
      } else {
        this.username = '';
        this.authenticated = false;
        this.authorities = [];
      }
      return callback && callback();
    });
  }
  logOut() {
    this.http.post(environment.baseUrl+'logout', {}).pipe( 
      finalize(() => {
        this.authenticated = false;
        this.authorities = [];
        this.username = '';
        this.router.navigateByUrl('/');
    })).subscribe();
  }
}
