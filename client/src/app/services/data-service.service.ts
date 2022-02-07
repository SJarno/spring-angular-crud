import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {
  greeting = "Moi";
  todos: any = [];
  todo!: any;
  selectedTodo?: any;

  constructor(private crudService: CrudService, private messageService: MessageService) {
    //bet practice is keep constructor minimal
    this.todo = { title: "", content: "" };

  }

  getGreeting() {
    this.crudService.getGreeting().subscribe(response => {
      console.log("Koko vastaus: ");
      console.log(response);
      console.log("Otsakkeet/avaimet: " + response.headers.keys());
      console.log("Status: " + response.status);
      console.log("Body: ");
      console.log(response.body);
      console.log("Tervehdys: " + response.body.greet);
      this.greeting = response.body.greet;

    });
  }
  saveNewTodo(todo: any) {
    this.crudService.saveNewTodo(todo).subscribe(response => {
      // if http status is created, add response, else do nothing
      if (response.status === 201) {
        this.todos.push(response.body);
      }


    });
  }
  getTodos() {
    this.crudService.getAllTodos().subscribe(response => {
      console.log("Get all todos, whole response: ");
      console.log(response.status);
      this.todos = response.body;

    });
  }
  updateTodo(todo: any, id: number) {
    return this.crudService.updateTodo(todo, id).subscribe(response => {
      console.log("Update");
      console.log(response);
      if (response.status === 200) {
        //clears todo from list, and adds the response from server
        this.removeFromArrayById(id);
        this.todos.push(response.body);
        this.selectedTodo = ""; //clear selection
      }
      
    });
  }
  deleteTodo(id: number) {
    this.crudService.deleteTodoById(id).subscribe(response => {
      console.log("Whole response");
      console.log(response);
      console.log("Body");
      console.log(response.body);
      this.removeFromArrayById(id);
      this.selectedTodo = "";
      
    });
  }
  removeFromArrayById(id: number) {
    this.todos.forEach((value: any, index: any) => {
      if (value.id === id) {
        this.todos.splice(index, 1);

      }

    });
  }
  toggleSelectedTodo(todo: any) {
    this.selectedTodo = todo;
  }
  getSelectedTodo() {
    return this.selectedTodo;
  }

}
