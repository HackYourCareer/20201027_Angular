import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FundamentalNgxCoreModule, DialogRef, DIALOG_REF, DIALOG_CONFIG, DialogConfig, DialogService, DialogComponent } from '@fundamental-ngx/core';
import { SignUpComponent } from './sign-up.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

describe('SignUpComponent', () => {
  let component: SignUpComponent;
  let fixture: ComponentFixture<SignUpComponent>;
  let dialogService: DialogService;
  const dialogRef = new DialogRef();
  const dialogConfig = new DialogConfig();

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule,
        NoopAnimationsModule,
        FormsModule,
        ReactiveFormsModule,
        FundamentalNgxCoreModule
      ],
      declarations: [
        SignUpComponent
      ],
      providers: [
        { provide: DIALOG_REF, useValue: dialogRef },
        { provide: DIALOG_CONFIG, useValue: {...dialogConfig, responsivePadding: true} }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    dialogService = TestBed.inject<DialogService>(DialogService);
  });

  afterEach(() => {
    TestBed.resetTestingModule();
  });

  it('should create', () => {
    fixture.autoDetectChanges();
    expect(component).toBeTruthy();
  });
});
