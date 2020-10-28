import { Component, OnInit, Inject } from '@angular/core';
import { DialogRef, DIALOG_REF } from '@fundamental-ngx/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserService } from '../services/user.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signupForm = new FormGroup({
    userName: new FormControl(''),
    passwords: new FormGroup({
      password: new FormControl(''),
      repeatedPassword: new FormControl(''),
    }),
    birthDate: new FormControl('')
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

}
