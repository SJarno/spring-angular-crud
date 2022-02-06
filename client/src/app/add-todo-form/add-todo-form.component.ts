import { Component, OnInit } from '@angular/core';
import { CrudService } from '../services/crud.service';
import { DataServiceService } from '../services/data-service.service';

@Component({
  selector: 'app-add-todo-form',
  templateUrl: './add-todo-form.component.html',
  styleUrls: ['./add-todo-form.component.css']
})
export class AddTodoFormComponent implements OnInit {
  todo!: any;
  
  todos: any = [];
  constructor(private crudService: CrudService, private dataService: DataServiceService) {
    this.todo = { title: "", content: "" };
  }

  ngOnInit(): void {
  }
  saveNewTodo() {
    this.dataService.saveNewTodo(this.todo);
    this.todo = { title: "", content: "" };//clears selection
  }

}
