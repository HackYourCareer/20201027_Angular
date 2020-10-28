import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BeerListComponent } from './beer-list/beer-list.component';
import { BeerDetailComponent } from './beer-detail/beer-detail.component';
import { LogInComponent } from './log-in/log-in.component';
import { HelloComponent } from './hello/hello.component';
import { AuthGuard } from './AuthGuard';

const routes: Routes = [
  { path: 'login', component: LogInComponent },
  { path: 'beer/:name', canActivate: [AuthGuard], component: BeerDetailComponent },
  { path: 'beers', canActivate: [AuthGuard], component: BeerListComponent },
  { path: '**', component: HelloComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
