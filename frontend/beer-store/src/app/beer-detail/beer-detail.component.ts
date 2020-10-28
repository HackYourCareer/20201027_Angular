import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { BeerService } from '../services/beer.service';
import { BeerDetailDto } from '../dto/beers.interface';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-beer-detail',
  templateUrl: './beer-detail.component.html',
  styleUrls: ['./beer-detail.component.scss']
})
export class BeerDetailComponent implements OnInit {

  beer: BeerDetailDto;

  constructor(private route: ActivatedRoute, private router: Router, private beerService: BeerService) { }

  ngOnInit() {
    const params = this.route.snapshot.params;
    this.beerService.loadBeerDetail(params.name).subscribe(b => this.beer = b);
  }

  mediaUrl() {
    return environment.hostUrl + this.beer.img;
  }

}
