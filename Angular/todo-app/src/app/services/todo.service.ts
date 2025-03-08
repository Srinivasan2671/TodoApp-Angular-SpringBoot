import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { Todo } from '../model/todo.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  private apiUrl = `${environment.API_BASE_URL}/todos`;

  constructor(private http: HttpClient, private authService: AuthService) {}

  getTodos(): Observable<Todo[]> {
    const userId = this.authService.getUserId();
    if (!userId) {
      throw new Error("User not logged in");
    }
    return this.http.get<Todo[]>(`${this.apiUrl}/user/${userId}`);
  }

  createTodo(todo: Todo): Observable<Todo> {
    const userId = this.authService.getUserId();
    if (!userId) {
      return throwError(() => new Error("User ID is required"));
    }
    
    const todoWithUser = { ...todo, userId };
    return this.http.post<Todo>(this.apiUrl, todoWithUser);
  }

  deleteTodos(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  updateTodo(todo: Todo): Observable<Todo> {
    const userId = this.authService.getUserId();
    if (!userId) {
      return throwError(() => new Error("User ID is required"));
    }
    
    const updatedTodo = { ...todo, userId };
    return this.http.put<Todo>(`${this.apiUrl}/${todo.id}`, updatedTodo);
  }
}
