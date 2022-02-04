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
    return this.http.get(this.url+"api/greet", { observe: 'response'});
  }
  getAllTodos(): Observable<any> {
    return this.http.get(this.url+"api/todos", { observe: 'response'});
  }
  saveNewTodo(todo : any): Observable<any> {
    return this.http.post(this.url+"api/add-todo", todo, { observe: 'response'});
  }
  /* updateTodo(todo: any): Observable<any> {
    return "";
  } */
  deleteTodoById(id : number): Observable<any> {
    return this.http.delete(this.url+"api/delete/"+id, {observe: 'response'});
  }
}
