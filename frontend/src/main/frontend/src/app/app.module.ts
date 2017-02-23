import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import {
  RouterModule,
  PreloadAllModules
} from '@angular/router';

import { BaseRequestOptions } from '@angular/http';

import { DropdownModule } from 'ng2-bootstrap/dropdown';
import { TabsModule } from 'ng2-bootstrap/tabs';
import { NAV_DROPDOWN_DIRECTIVES } from './shared/nav-dropdown.directive';

import { ChartsModule } from 'ng2-charts/ng2-charts';
import { SIDEBAR_TOGGLE_DIRECTIVES } from './shared/sidebar.directive';
import { AsideToggleDirective } from './shared/aside.directive';


// Routing Module
import { AppRoutingModule } from './app.routing';
import { AppComponent } from './app.component';
import { AlertComponent, AlertService } from './shared/index';
import { BreadcrumbsComponent } from './shared/breadcrumb.component';


import { AuthGuard, AuthenticationService } from './auth/index';
import { DashboardComponent } from './dashboard/index';
import { LoginComponent } from './auth/login/index';
import { RegisterComponent } from './auth/register/index';
import { TechnicienComponent, TechnicienService } from './technicien/index';
import { ParametrageComponent, ParametrageService } from './parametrage/index';

import { Ng2SmartTableModule } from 'ng2-smart-table';

import { HighlightDirective } from './shared/highlight.directive';
import { UnlessDirective } from './shared/unless.directive';


import 'rxjs/Rx';

@NgModule({

  declarations: [
    AppComponent,
    AlertComponent,
    NAV_DROPDOWN_DIRECTIVES,
    BreadcrumbsComponent,
    SIDEBAR_TOGGLE_DIRECTIVES,
    AsideToggleDirective,
    RegisterComponent,
    LoginComponent,
    TechnicienComponent,
    DashboardComponent,
    ParametrageComponent,
    HighlightDirective,
    UnlessDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DropdownModule.forRoot(),
    TabsModule.forRoot(),
    RouterModule,
    ChartsModule,
    FormsModule,
    HttpModule,
    Ng2SmartTableModule
  ],
  providers: [{
    provide: LocationStrategy,
    useClass: HashLocationStrategy,

  },
    AuthGuard,
    AlertService,
    AuthenticationService,
    BaseRequestOptions,
    TechnicienService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
