import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  messages: string[] = [];
  errorMessage?: string;
  constructor() { }

  add(message: string) {
    this.messages.push(message);
  }
  clear() {
    this.messages = [];
  }
  addErrorMessage(message: string) {
    this.errorMessage = message;
  }
  clearErrorMessage() {
    this.errorMessage = "";
  }
}
