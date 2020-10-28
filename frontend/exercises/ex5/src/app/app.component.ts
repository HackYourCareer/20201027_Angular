import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'beer-store';

  constructor(private authService: AuthService) {
  }

  signUpClick() {
    console.log('You want to sign up');
  }

  isLogin(): boolean {
    return this.authService.isLoggedIn();
  }

  logOut() {
    this.authService.logout();
  }
}
