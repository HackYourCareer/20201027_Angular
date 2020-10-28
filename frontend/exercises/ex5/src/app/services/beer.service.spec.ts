import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { BeerService } from './beer.service';

describe('BeerService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientModule
    ]
  }));

  it('should be created', () => {
    const service: BeerService = TestBed.inject(BeerService);
    expect(service).toBeTruthy();
  });
});
