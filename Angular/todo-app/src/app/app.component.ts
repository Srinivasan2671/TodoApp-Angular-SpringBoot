import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'todo-app';

  constructor(public authService: AuthService, private router: Router){}
  
  logout(): void{
    this.authService.logout();
    this.router.navigate(['/login']);// Redirect to login after logout
  }
}


