import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpErrorResponse } from '@angular/common/http'
import { catchError, Observable, of, tap, throwError } from 'rxjs';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class CrudService {
  url: string = environment.baseUrl;
  constructor(private http: HttpClient, private messagesService: MessageService) { }

  getGreeting(): Observable<any> {
    return this.http.get(this.url + "api/greet", { observe: 'response' })
      .pipe(
        tap(_ => this.messagesService.add("Fetch greeting")),
        catchError(this.handleError<any>('getGreeting', []))
      );
  }
  getAllTodos(): Observable<any> {
    return this.http.get(this.url + "api/todos", { observe: 'response' })
      .pipe(
        tap(_ => this.messagesService.add("Fetch all todos")),
        catchError(this.handleError<any>('getAllTodos', []))
    );
  }
  saveNewTodo(todo: any): Observable<any> {
    return this.http.post(this.url + "api/add-todo", todo, { observe: 'response' })
      .pipe(
        tap(_ => this.messagesService.add("Saved")),
        catchError(this.handleError<any>('saveNewTodo', []))
    );
  }
  updateTodo(todo: any, id: number): Observable<any> {
    return this.http.put(this.url + "api/update/" + id, todo, { observe: 'response' })
      .pipe(
        tap(_ => this.messagesService.add("Updated")),
        catchError(this.handleError<any>('updateTodo', []))
    );
  }
  deleteTodoById(id: number): Observable<any> {
    return this.http.delete(this.url + "api/delete/" + id, { observe: 'response' })
      .pipe(
        tap(_ => this.messagesService.add("Deleted")),
        catchError(this.handleError<any>('deleteTodoById', []))
    );
  }
  
private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // Prin the error on console:
    console.error("An error occured: "+error.error); // log to console instead

    // Add message error to service for display:
    // full error
    //this.messagesService.addErrorMessage(`${operation} failed: ${error.message}`);
    // message from backend:
    this.messagesService.addErrorMessage(`${operation} ${error.error}`);
    

    // Should return empty result:
    return of(result as T);
  };
}

}
