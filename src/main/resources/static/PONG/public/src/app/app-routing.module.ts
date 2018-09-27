import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from './main/main.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LeftComponent } from './left/left.component';
import { RightComponent } from './right/right.component';
import { UsersComponent } from './left/users/users.component';
import { CompAComponent } from './left/comp-a/comp-a.component';
import { CompBComponent } from './left/comp-b/comp-b.component';
import { CompCComponent } from './left/comp-c/comp-c.component';


const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: DashboardComponent,
  children: [
    { path: '', component: UsersComponent, outlet: 'left'},
    { path: 'A', component: CompAComponent, outlet: 'left'},
    { path: 'B', component: CompBComponent, outlet: 'left'},
    { path: 'C', component: CompCComponent, outlet: 'left'},
    { path: 'Ss', component: UsersComponent, outlet: 'right'},
    { path: 'Aa', component: CompAComponent, outlet: 'right'},
    { path: 'Bb', component: CompBComponent, outlet: 'right'},
    { path: 'Cc', component: CompCComponent, outlet: 'right'},
  ]
},
  {path: 'full', component: MainComponent },
  {path: '**',redirectTo: '/home',pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
