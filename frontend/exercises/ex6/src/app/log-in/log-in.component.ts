import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { LoginDto } from '../dto/users.interface';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.scss']
})
export class LogInComponent implements OnInit {

  model: LoginDto = { userName: '', password: '' };
  showErrorBox = false;

  constructor(private userService: UserService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  login() {
    this.showErrorBox = false;
    this.userService.login(this.model).subscribe((resp) => {
      this.authService.login(resp);
      this.router.navigate(['/beers']);
    }, error => {
      this.showErrorBox = true;
    });
  }

}
