import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';


@NgModule({
   declarations: [
      AppComponent,
      HeaderComponent,
      routingComponents,

   ],
   imports: [ // Import modules, except those that are lazy loaded (AdministrationModule)
      BrowserModule,
      AppRoutingModule,
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
