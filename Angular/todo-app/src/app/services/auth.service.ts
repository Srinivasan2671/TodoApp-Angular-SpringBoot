import { isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private apiUrl = 'http://localhost:4200/api/auth';

  constructor(
    private http: HttpClient,
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  login(userId: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, { userId, password }).pipe(
      tap(response => {
        if (response.userId && this.isBrowser()) {  // ✅ Check if in browser
          localStorage.setItem('isLoggedIn', 'true');
          localStorage.setItem('userId', response.userId.toString());
          this.router.navigate(['/todos']);
        } else {
          alert("Login failed. Please try again.");
        }
      }),
      catchError(error => {
        alert("Invalid credentials. Please try again.");
        return throwError(() => new Error(error));
      })
    );
  }

  register(userId: string, password: string): Observable<any> {
    userId = userId.trim();
    password = password.trim();

    return this.http.post<any>(`${this.apiUrl}/register`, { userId, password }).pipe(
      tap(() => {
        alert("Registration successful! Redirecting to login...");
        this.router.navigate(['/login']);
      }),
      catchError(error => {
        let errorMsg = "Registration failed. Try again.";
        if (error.error?.error) {
          errorMsg = error.error.error;
        }
        alert(errorMsg);
        return throwError(() => new Error(errorMsg));
      })
    );
  }

  logout(): void {
    if (this.isBrowser()) {  // ✅ Ensure browser environment
      localStorage.removeItem('isLoggedIn');
      localStorage.removeItem('userId');
    }
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return this.isBrowser() && localStorage.getItem('isLoggedIn') === 'true';
  }

  getUserId(): string | null {
    return this.isBrowser() ? localStorage.getItem('userId') : null;
  }

  private isBrowser(): boolean {
    return typeof window !== 'undefined' && isPlatformBrowser(this.platformId);
  }
}
