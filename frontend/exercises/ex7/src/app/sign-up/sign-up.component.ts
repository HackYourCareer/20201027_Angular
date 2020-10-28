import { Component, OnInit, Inject } from '@angular/core';
import { DialogRef, DIALOG_REF } from '@fundamental-ngx/core';
import { AbstractControl, AsyncValidatorFn, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signupForm = new FormGroup({
    userName: new FormControl('', [Validators.required], [this.usernameValidator(this.userService)]),
    passwords: new FormGroup({
      password: new FormControl('', [Validators.min(1)]),
      repeatedPassword: new FormControl('', [Validators.min(1)]),
    }, this.validatePasswords),
    birthDate: new FormControl('', this.validateAge)
  });

  constructor(@Inject(DIALOG_REF) public dialogRef: DialogRef, private userService: UserService) { }

  ngOnInit() {
  }

  dismiss() {
    this.dialogRef.dismiss();
  }

  signup() {
    this.userService.createAccount({ userName: this.signupForm.value.userName, password: this.signupForm.value.passwords.password })
      .subscribe((resp: HttpResponse<any>) => {
        this.dialogRef.close();
      }, (err) => {
        console.log(err);
      });
  }

  usernameValidator(userService: UserService): AsyncValidatorFn {
    return (control: AbstractControl): Observable<any> => {
      return userService.validateUsername(control.value).pipe(map(res => {
        return res.result ? null : { usernameAlreadyExist: true };
      }));
    };
  }

  validatePasswords(control: AbstractControl) {
    if (control.value.password === control.value.repeatedPassword) {
      return null;
    }
    return { passwordsDoNotMatch: true };
  }

  validateAge(control: AbstractControl) {
    if (2019 - control.value.year > 18) {
      return null;
    }
    return { ageBelow18: true };
  }
}
