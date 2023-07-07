import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoaderModule } from './components/loader/loader.module';
import { LoaderInterceptor } from './components/loader/loader.interceptor';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { NgxPaginationModule } from 'ngx-pagination';
import { HeaderComponent } from './components/header/header.component';
import { NavComponent } from './components/nav/nav.component';

// import { NgxSpinnerModule } from "ngx-spinner";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Interceptor } from './app.interceptor.module';
import { DuasCasasAposPonto } from './components/pipes/DuasCasasAposPonto.pipe';
import { NumericOnlyDirective } from './components/diretivas/numeric-only.directive';
import { NumericOnlyDirective2 } from './components/diretivas/numeric-only2.directive';
import { NgxMaskModule } from 'ngx-mask';

import { HomeComponent } from './pages/home/home.component';
import { PersonagemComponent } from './pages/personagem/personagem.component';
import { LoginComponent } from './pages/login/login.component';
import { UsuarioComponent } from './pages/usuario/usuario.component';
import { ClasseComponent } from './pages/classe/classe.component';
import { RacaComponent } from './pages/raca/raca.component';
import { TendenciaComponent } from './pages/tendencia/tendencia.component';




@NgModule({
  declarations: [
    AppComponent,

    HeaderComponent,
    NavComponent,
    DuasCasasAposPonto,
    NumericOnlyDirective,
    NumericOnlyDirective2,

    LoginComponent,
    HomeComponent,
    PersonagemComponent,
    UsuarioComponent,
    ClasseComponent,
    RacaComponent,
    TendenciaComponent


  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule,
    LoaderModule,
    NgxPaginationModule,
    NgMultiSelectDropDownModule.forRoot(),
    Interceptor,
    NgxMaskModule.forRoot({
      dropSpecialCharacters: true
    })
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: LoaderInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
