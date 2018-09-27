import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { LeftComponent } from './left/left.component';
import { MainComponent } from './main/main.component';
import { RightComponent } from './right/right.component';
import { UserProfComponent } from './user-prof/user-prof.component';
import { UsersComponent } from './left/users/users.component';

import { UsersService } from './users.service';
import { OneComponent } from './one/one.component';
import { CompAComponent } from './left/comp-a/comp-a.component';
import { CompBComponent } from './left/comp-b/comp-b.component';
import { CompCComponent } from './left/comp-c/comp-c.component';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    LeftComponent,
    MainComponent,
    RightComponent,
    UserProfComponent,
    UsersComponent,
    OneComponent,
    CompAComponent,
    CompBComponent,
    CompCComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
  ],
  providers: [
    UsersService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
