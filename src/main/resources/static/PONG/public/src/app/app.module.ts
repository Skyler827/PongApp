import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { LeftComponent } from './left/left.component';
import { MainComponent } from './main/main.component';
import { RightComponent } from './right/right.component';
import { UserProfComponent } from './user-prof/user-prof.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    LeftComponent,
    MainComponent,
    RightComponent,
    UserProfComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
