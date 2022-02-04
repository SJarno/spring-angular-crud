import { Component } from '@angular/core';
import { CrudService } from './services/crud.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  greeting = "Terve"
  todos: any = [];
  todo!:any;

  constructor(private crudService: CrudService) {
    this.todo = {title:"", content:""};
    

  }
  ngOnInit() {
    this.getGreeting();
    this.getTodos();
    
  }
  getGreeting() {
    this.crudService.getGreeting().subscribe(response => {
      console.log("Koko vastaus: ");
      console.log(response)
      console.log("Otsakkeet/avaimet: " + response.headers.keys());
      console.log("Status: " + response.status);
      console.log("Body: ");
      console.log(response.body);
      console.log("Tervehdys: "+response.body.greet);
      this.greeting = response.body.greet;
      
    });
  }
  getTodos() {
    this.crudService.getAllTodos().subscribe(response => {
      console.log("Get all todos, whole response: ");
      console.log(response);
      this.todos = response.body;
    });
  }
  saveNewTodo() {
    this.crudService.saveNewTodo(this.todo).subscribe(response => {
      console.log("Whole response")
      console.log(response);
      console.log("Body");
      console.log(response.body);
      this.todos.push(response.body);
      this.todo = {title:"", content:""};
    });
  }
}
