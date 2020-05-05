import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
   declarations: [
      AppComponent,
      HeaderComponent,
      routingComponents,
      FooterComponent
   ],
   imports: [
      //Importmodules, except those that are lazy loaded (AdministrationModule)
      BrowserModule,
      AppRoutingModule,
      BrowserAnimationsModule
   ],
   providers: [
      CookieService
   ],
   schemas: [
      CUSTOM_ELEMENTS_SCHEMA
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
