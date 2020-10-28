import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  static KEY_X_AUTH_TOKEN = 'xAuthToken';

  constructor(private router: Router) { }

  public isLoggedIn(): boolean {
    return sessionStorage.getItem(AuthService.KEY_X_AUTH_TOKEN) != null;
  }

  public getToken(): string {
    return sessionStorage.getItem(AuthService.KEY_X_AUTH_TOKEN);
  }

  public login(xAuthToken: string) {
    sessionStorage.setItem(AuthService.KEY_X_AUTH_TOKEN, xAuthToken);
  }

  public logout() {
    sessionStorage.removeItem(AuthService.KEY_X_AUTH_TOKEN);
    this.router.navigate(['/']);
  }

}
