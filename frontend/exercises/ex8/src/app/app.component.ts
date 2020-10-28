import { Component } from '@angular/core';
import { DialogService } from '@fundamental-ngx/core';
import { SignUpComponent } from './sign-up/sign-up.component';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'beer-store';

  constructor(private dialogService: DialogService, private authService: AuthService) { }

  signUpClick() {
    this.dialogService.open(SignUpComponent, {
      backdropClickCloseable: false,
      width: '400px',
      responsivePadding: true
    });
  }

  isLogin(): boolean {
    return this.authService.isLoggedIn();
  }

  logOut() {
    this.authService.logout();
  }
}
