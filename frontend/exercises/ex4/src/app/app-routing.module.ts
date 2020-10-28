import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BeerDetailComponent } from './beer-detail/beer-detail.component';
import { BeerListComponent } from './beer-list/beer-list.component';


const routes: Routes = [
  { path: 'beer/:name', component: BeerDetailComponent },
  { path: '**', component: BeerListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
