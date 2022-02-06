import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyTodoComponent } from './modify-todo.component';

describe('ModifyTodoComponent', () => {
  let component: ModifyTodoComponent;
  let fixture: ComponentFixture<ModifyTodoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModifyTodoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyTodoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
