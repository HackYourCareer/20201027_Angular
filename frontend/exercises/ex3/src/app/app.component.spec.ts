import { TestBed, async, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';
import { FundamentalNgxCoreModule } from '@fundamental-ngx/core';
import { AppComponent } from './app.component';
import { BeerListComponent } from './beer-list/beer-list.component';
import { BeerListItemComponent } from './beer-list-item/beer-list-item.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { BeerService } from './services/beer.service';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let beerService: BeerService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        NoopAnimationsModule,
        HttpClientModule,
        FundamentalNgxCoreModule
      ],
      declarations: [
        AppComponent,
        BeerListComponent,
        BeerListItemComponent
      ],
      providers: [
        BeerService
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    beerService = TestBed.inject<BeerService>(BeerService);
  });

  it('should create the app', () => {
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'beer-store'`, () => {
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('beer-store');
  });

  it('should render title', () => {
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('#shellbarTitle').textContent).toContain('Beer Portal');
  });
});
