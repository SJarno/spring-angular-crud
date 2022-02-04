import { Injectable } from '@angular/core';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {
  greeting = "Moi";
  todos: any = [];
  todo!: any;
  constructor(private crudService: CrudService) {
    this.getGreeting();
    this.getTodos();
    this.todo = { title: "", content: "" };

  }


  getGreeting() {
    this.crudService.getGreeting().subscribe(response => {
      console.log("Koko vastaus: ");
      console.log(response)
      console.log("Otsakkeet/avaimet: " + response.headers.keys());
      console.log("Status: " + response.status);
      console.log("Body: ");
      console.log(response.body);
      console.log("Tervehdys: " + response.body.greet);
      this.greeting = response.body.greet;

    });
  }
  saveNewTodo(todo : any) {
    this.crudService.saveNewTodo(todo).subscribe(response => {
      console.log("Whole response")
      console.log(response);
      console.log("Body");
      console.log(response.body);
      this.todos.push(response.body);
      //this.todo = { title: "", content: "" };
    });
  }
  getTodos() {
    this.crudService.getAllTodos().subscribe(response => {
      console.log("Get all todos, whole response: ");
      console.log(response);
      this.todos = response.body;
    });
  }
  deleteTodo(id: number) {
    this.crudService.deleteTodoById(id).subscribe(response => {
      console.log("Whole response");
      console.log(response);
      console.log("Body");
      console.log(response.body);
      this.removeFromArrayById(id);
    });
  }
  removeFromArrayById(id: number) {
    this.todos.forEach((value: any, index: any) => {
      if (value.id === id) {
        this.todos.splice(index, 1);

      }

    });
  }
}
