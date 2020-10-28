import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FundamentalNgxCoreModule } from '@fundamental-ngx/core';
import { BeerListComponent } from './beer-list/beer-list.component';
import { BeerListItemComponent } from './beer-list-item/beer-list-item.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BeerDetailComponent } from './beer-detail/beer-detail.component';
import { HelloComponent } from './hello/hello.component';
import { LogInComponent } from './log-in/log-in.component';
import { FormsModule } from '@angular/forms';
import { AuthTokenInterceptor } from './interceptors/AuthTokenInterceptor';

@NgModule({
  declarations: [
    AppComponent,
    BeerListComponent,
    BeerListItemComponent,
    BeerDetailComponent,
    HelloComponent,
    LogInComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FundamentalNgxCoreModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthTokenInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
