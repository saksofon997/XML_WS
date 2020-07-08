import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SelectDropDownModule } from 'ngx-select-dropdown';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatIconModule } from '@angular/material/icon';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { FooterComponent } from './footer/footer.component';
import { SharedModule } from './shared/shared.module';
import { TokenInterceptor } from './services/token.interceptor';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgHttpLoaderModule } from 'ng-http-loader';
import { RegisterComponent } from './register/register.component';

@NgModule({
   declarations: [
      AppComponent,
      HeaderComponent,
      routingComponents,
      FooterComponent,
      LoginComponent,
      RegisterComponent
   ],
   imports: [
      //Importmodules except lazy loaded
      AppRoutingModule,
      BrowserAnimationsModule,
      SharedModule,
      NgbModule,
      HttpClientModule,
      HttpModule,
      SelectDropDownModule,
      MatIconModule,
      FormsModule,
      ReactiveFormsModule,
      NgHttpLoaderModule.forRoot(),
   ],
   providers: [
      CookieService,
      {
         provide: HTTP_INTERCEPTORS,
         useClass: TokenInterceptor,
         multi: true
      }
   ],
   schemas: [
      CUSTOM_ELEMENTS_SCHEMA
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
