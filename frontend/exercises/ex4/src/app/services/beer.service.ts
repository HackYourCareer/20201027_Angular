import { Injectable } from '@angular/core';
import { BeerDetailDto, BeerDto } from '../dto/beers.interface';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BeerService {

  BEERS_ENDPOINT = `${environment.hostUrl}/test/beer`;

  constructor(private http: HttpClient) { }

  loadBeers(): Observable<BeerDto[]> {
    const beers$ = this.http.get(this.BEERS_ENDPOINT) as Observable<BeerDto[]>;
    return beers$;
  }

  loadBeerDetail(name: string): Observable<BeerDetailDto> {
    return this.http.get(this.BEERS_ENDPOINT + '/' + name) as Observable<BeerDetailDto>;
  }

}
