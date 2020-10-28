import { Injectable } from '@angular/core';
import { LoginDto } from '../dto/users.interface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  CREATE_ACCOUNT_ENDPOINT = `${environment.hostUrl}/user`;
  VALIDATE_USERNAME_ENDPOINT = `${environment.hostUrl}/user/`;
  LOGIN_ENDPOINT = `${environment.hostUrl}/login`;

  constructor(private http: HttpClient) { }

  //   createAccount(newUser: LoginDto): Observable<any> {
  //     return this.http.post(this.CREATE_ACCOUNT_ENDPOINT, newUser, {
  //       headers: this.headers
  //     });
  //   }

  //   validateUsername(username: string): Observable<UsernameValidationResult> {
  //     if (!username) {
  //       return of({ result: true, message: null });
  //     }
  //     return this.http.get(this.VALIDATE_USERNAME_ENDPOINT + username) as Observable<UsernameValidationResult>;
  //   }

  login(loginData: LoginDto): Observable<any> {
    return this.http.post(this.LOGIN_ENDPOINT, loginData, { responseType: 'text' });
  }

}
