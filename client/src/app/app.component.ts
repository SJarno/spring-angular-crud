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
  deleteTodo(id: number) {
    this.dataService.deleteTodo(id);
  }
  toggleModify(event: any) {
    console.log(event);
  }

}
