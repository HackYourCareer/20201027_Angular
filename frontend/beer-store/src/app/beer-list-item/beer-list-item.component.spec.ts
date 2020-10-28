import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FundamentalNgxCoreModule } from '@fundamental-ngx/core';
import { BeerListItemComponent } from './beer-list-item.component';

describe('BeerListItemComponent', () => {
  let component: BeerListItemComponent;
  let fixture: ComponentFixture<BeerListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        FundamentalNgxCoreModule
      ],
      declarations: [
        BeerListItemComponent
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BeerListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  afterEach(() => {
    TestBed.resetTestingModule();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
