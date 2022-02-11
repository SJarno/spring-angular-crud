import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AddTodoFormComponent } from './add-todo-form/add-todo-form.component';
import { ListTodosComponent } from './list-todos/list-todos.component';
import { ModifyTodoComponent } from './modify-todo/modify-todo.component';
import { MessagesComponent } from './messages/messages.component';
import { LoginComponent } from './login/login.component';
import { NavigationComponent } from './navigation/navigation.component';
import { MainComponent } from './main/main.component';


@NgModule({
  declarations: [
    AppComponent,
    AddTodoFormComponent,
    ListTodosComponent,
    ModifyTodoComponent,
    MessagesComponent,
    LoginComponent,
    NavigationComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
