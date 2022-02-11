import { Component, OnInit } from '@angular/core';
import { DataServiceService } from '../services/data-service.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private dataService: DataServiceService) { }

  ngOnInit() {
    //Call methods on init, best practice
    this.dataService.getGreeting();
    this.dataService.getTodos();
  }
  getGreeting() {
    return this.dataService.greeting;
  }

}
