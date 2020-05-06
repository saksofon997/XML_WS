import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SelectDropDownModule } from 'ngx-select-dropdown';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';

@NgModule({
   declarations: [
      AppComponent,
      HeaderComponent,
      routingComponents,
   ],
   imports: [ // Import modules, except those that are lazy loaded (AdministrationModule)
      BrowserModule,
      AppRoutingModule,
      BrowserAnimationsModule,
      HttpClientModule,
      HttpModule,
      SelectDropDownModule 
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
