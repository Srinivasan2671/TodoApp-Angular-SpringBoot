import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  userId: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService,private router: Router){}

  login(): void{
    this.errorMessage = '';
    this.authService.login(this.userId, this.password).subscribe({
      next: () =>{
        localStorage.setItem('isLoggedIn', 'true');
        this.router.navigate(['/todos']);
      },
      error: (err) => {
        
        this.errorMessage = 'Invalid userId or password';
      }
    });
  }
}
