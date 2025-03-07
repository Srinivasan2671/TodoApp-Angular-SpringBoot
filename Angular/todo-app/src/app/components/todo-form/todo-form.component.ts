import { Component } from '@angular/core';
import { Todo } from '../../model/todo.model';
import { AuthService } from '../../services/auth.service';
import { TodoService } from '../../services/todo.service';

@Component({
  selector: 'app-todo-form',
  standalone: false,
  templateUrl: './todo-form.component.html',
  styleUrl: './todo-form.component.css'
})
export class TodoFormComponent {
  todoTitle: string = '';
  todoDescription: string = '';
  todoIsDone: boolean = false;
  errorMessage: string = '';
  
  constructor(private todoService: TodoService, private authService: AuthService) {}

  createTodo(): void {
    if (!this.todoTitle.trim() || !this.todoDescription.trim()) return;

    const userId = localStorage.getItem('userId');  //Get userId from localStorage
    if (!userId) {
      
      alert("Session expired. Please log in again.");
      return;
    }

    const newTodo: Todo = { 
      title: this.todoTitle,
      description: this.todoDescription,
      isDone: this.todoIsDone === true,
      userId: userId
    };

    this.todoService.createTodo(newTodo).subscribe({
      next: () => {
        
        this.todoTitle = ''; // Reset input field
        this.todoDescription = '';
        this.todoIsDone = false;
        this.errorMessage = '';
      },
      error: () => {
        this.errorMessage = "Failed to create todo. Please try again later.";
      }
    });
  }
}
