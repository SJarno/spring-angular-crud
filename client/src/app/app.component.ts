import { Component } from '@angular/core';
import { CrudService } from './services/crud.service';
import { DataServiceService } from './services/data-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  todo!: any;
  selectedTodo?: any;

  constructor(private crudService: CrudService, private dataService: DataServiceService) {
    this.todo = { title: "", content: "" };

  }
  ngOnInit() {
  }
  getGreeting() {
    return this.dataService.greeting;
  }
  getTodos() {
    return this.dataService.todos;
  }
  updateTodo(todo: any, id: number) {
    console.log(todo);
    console.log(id);
    this.dataService.updateTodo(todo, id);
  }
  deleteTodo(id: number) {
    this.dataService.deleteTodo(id);
  }
  toggleModify(todo: any) {
    this.selectedTodo = todo;

  }

}
