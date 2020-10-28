import { Component, OnInit } from '@angular/core';
import { BeerService } from '../services/beer.service';
import { BeerDto } from '../dto/beers.interface';

@Component({
  selector: 'app-beer-list',
  templateUrl: './beer-list.component.html',
  styleUrls: ['./beer-list.component.scss']
})
export class BeerListComponent implements OnInit {

  beers: BeerDto[];

  constructor(private beerService: BeerService) { }

  ngOnInit() {
    this.beerService.loadBeers().subscribe(beers => this.beers = beers);
  }

}
