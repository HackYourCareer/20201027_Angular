import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FundamentalNgxCoreModule } from '@fundamental-ngx/core';
import { BeerListComponent } from './beer-list/beer-list.component';
import { BeerListItemComponent } from './beer-list-item/beer-list-item.component';
import { HttpClientModule } from '@angular/common/http';
import { BeerDetailComponent } from './beer-detail/beer-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    BeerListComponent,
    BeerListItemComponent,
    BeerDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FundamentalNgxCoreModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
