import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  userId: string = '';
  password: string = '';
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    this.errorMessage = '';
    this.successMessage = '';

    const trimmedUserId = this.userId.trim();
    const trimmedPassword = this.password.trim();

    if (!trimmedUserId || !trimmedPassword) {
      this.errorMessage = 'User ID and Password are required!';
      return;
    }

    this.authService.register(trimmedUserId, trimmedPassword).subscribe({
      next: () => {
        this.successMessage = 'Registration successful! Redirecting to login...';
        this.errorMessage = '';

        
        this.userId = '';
        this.password = '';

        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error) => {
        console.error('Registration error:', error);
        this.errorMessage = error.error?.error || 'Registration failed!';
        this.successMessage = '';
      }
    });
  }
}
