import { Component, OnInit } from '@angular/core';
import { Todo } from '../../model/todo.model';
import { TodoService } from '../../services/todo.service';

@Component({
  selector: 'app-todo-list',
  standalone: false,
  templateUrl: './todo-list.component.html',
  styleUrl: './todo-list.component.css'
})
export class TodoListComponent implements OnInit {
  todos: Todo[] = [];
  
  editingTodoId: number | null = null;
  editTitle: string = '';
  editDescription: string = '';
  editIsDone: boolean = false;

  constructor(private todoService: TodoService) {}

  ngOnInit(): void {
    this.loadTodos();
  }

  loadTodos(): void {
    this.todoService.getTodos().subscribe((data) => {
      this.todos = data;
    });
  }

  deleteTodo(id: number): void {
    this.todoService.deleteTodos(id).subscribe(() => {
      this.todos = this.todos.filter(todo => todo.id !== id);
    });
  }

  editTodo(todo: Todo): void {
    this.editingTodoId = todo.id!;
    this.editTitle = todo.title;
    this.editDescription = todo.description;
    this.editIsDone = todo.isDone;
  }

  saveTodo(id: number): void {
    const updatedTodo: Todo = {
      id: id,
      title: this.editTitle,
      description: this.editDescription,
      isDone: this.editIsDone,
      userId: ''
    };

    this.todoService.updateTodo(updatedTodo).subscribe((updated) => {
      this.todos = this.todos.map(todo => (todo.id === id ? updated : todo));
      this.editingTodoId = null;
    });
  }

  cancelEdit(): void {
    this.editingTodoId = null;
  }
}
