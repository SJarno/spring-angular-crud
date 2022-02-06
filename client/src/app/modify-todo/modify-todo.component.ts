import { Component, OnInit } from '@angular/core';
import { DataServiceService } from '../services/data-service.service';

@Component({
  selector: 'app-modify-todo',
  templateUrl: './modify-todo.component.html',
  styleUrls: ['./modify-todo.component.css']
})
export class ModifyTodoComponent implements OnInit {

  constructor(private dataService: DataServiceService) { }

  ngOnInit(): void {
  }
  updateTodo(todo: any, id: number) {
    this.dataService.updateTodo(todo, id);
  }
  deleteTodo(id: number) {
    this.dataService.deleteTodo(id);
    
  }
  getSelectedTodo() {
    return this.dataService.selectedTodo;
  }

}
