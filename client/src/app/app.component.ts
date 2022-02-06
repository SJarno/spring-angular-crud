import { Component } from '@angular/core';
import { CrudService } from './services/crud.service';
import { DataServiceService } from './services/data-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  constructor(private crudService: CrudService, private dataService: DataServiceService) {

  }
  ngOnInit() {
  }
  getGreeting() {
    return this.dataService.greeting;
  }


}
