import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'beer-store';

  signUpClick() {
    console.log('You want to sign up');
  }

  logInClick() {
    console.log('You want to log out');
  }
}
