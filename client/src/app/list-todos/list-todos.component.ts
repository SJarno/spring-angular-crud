import { Component, OnInit } from '@angular/core';
import { DataServiceService } from '../services/data-service.service';

@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})
export class ListTodosComponent implements OnInit {

  constructor(private dataService: DataServiceService) { }

  ngOnInit(): void {
  }
  getTodos() {
    return this.dataService.todos;
  }
  toggleModify(todo: any) {
    this.dataService.toggleSelectedTodo(todo);

  }

}
