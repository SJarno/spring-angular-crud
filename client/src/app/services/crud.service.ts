import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CrudService {
  url: string = environment.baseUrl;
  constructor(private http: HttpClient) { }

  getGreeting(): Observable<any> {
    return this.http.get(this.url+"api/greet");
  }
}
