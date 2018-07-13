import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatChipsModule } from '@angular/material';
import { CommonModule } from '@angular/common';
import { DataTablesModule } from 'angular-datatables';
import {HttpModule} from '@angular/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/template/header/header.component';
import { FooterComponent } from './components/template/footer/footer.component';
import { SettingComponent } from './components/setting/setting.component';
import { MenuComponent } from './components/template/menu/menu.component';
import { SidebarComponent } from './components/template/sidebar/sidebar.component';
import { DashboardComponent } from './components/modules/dashboard/dashboard.component';
import { NetworkStatusComponent } from './components/modules/network-status/network-status.component';
import { AppRoutingModule } from './app-routing.module';
import { ActiveComponent } from './components/modules/network-status/active/active.component';
import { InActiveComponent } from './components/modules/network-status/in-active/in-active.component';
import { HaltedComponent } from './components/modules/network-status/halted/halted.component';
import { ErrorPageComponent } from './components/error/error-page/error-page.component';
import { NetworkAvailabilityComponent } from './components/modules/dashboard/network-availability/network-availability.component';
import { StatusService } from './components/modules/dashboard/status.service';
import { LoggingService } from './logging.service';
import { KpiComponent } from './components/modules/kpi/kpi.component';
import { RncComponent } from './components/modules/kpi/rnc/rnc.component';
import { BscComponent } from './components/modules/kpi/bsc/bsc.component';
import { LteComponent } from './components/modules/kpi/lte/lte.component';
import { KpiService } from './components/modules/kpi/kpi.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SettingComponent,
    MenuComponent,
    SidebarComponent,
    DashboardComponent,
    NetworkStatusComponent,
    ActiveComponent,
    InActiveComponent,
    HaltedComponent,
    ErrorPageComponent,
    NetworkAvailabilityComponent,
    KpiComponent,
    RncComponent,
    BscComponent,
    LteComponent
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
],
  imports: [
   BrowserModule,
   AppRoutingModule,
   BrowserAnimationsModule,
   MatChipsModule,
   CommonModule,
   DataTablesModule,
   HttpModule
    ],
  providers: [ StatusService, LoggingService, KpiService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
