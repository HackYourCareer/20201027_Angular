import { Component, OnInit, Input } from '@angular/core';
import { BeerDto } from '../dto/beers.interface';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-beer-list-item',
  templateUrl: './beer-list-item.component.html',
  styleUrls: ['./beer-list-item.component.scss']
})
export class BeerListItemComponent implements OnInit {

  @Input() beer: BeerDto;

  constructor() { }

  ngOnInit() {
  }

  mediaUrl() {
    return environment.hostUrl + this.beer.img;
  }
}
