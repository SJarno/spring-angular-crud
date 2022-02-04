import { Component } from '@angular/core';
import { CrudService } from './services/crud.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';

  constructor(private crudService: CrudService) {
    this.crudService.getGreeting().subscribe(data => {
      console.log(data);
      this.title = data.greet;
    })
  }
}
