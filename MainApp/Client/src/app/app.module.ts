import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SideListComponent } from './administration/side-list/side-list.component';
import { AdminTableComponent } from './administration/admin-table/admin-table.component';

const AdministrationComponents = [SideListComponent, AdminTableComponent]

@NgModule({
   declarations: [
      AppComponent,
      HeaderComponent,
      routingComponents,
      AdministrationComponents

   ],
   imports: [
      BrowserModule,
      AppRoutingModule
   ],
   providers: [
      CookieService
   ],
   schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
