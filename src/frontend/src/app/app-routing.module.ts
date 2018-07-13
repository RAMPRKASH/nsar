import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DashboardComponent } from './components/modules/dashboard/dashboard.component';
import { NetworkStatusComponent } from './components/modules/network-status/network-status.component';
import { ActiveComponent } from './components/modules/network-status/active/active.component';
import { InActiveComponent } from './components/modules/network-status/in-active/in-active.component';
import { HaltedComponent } from './components/modules/network-status/halted/halted.component';
import { ErrorPageComponent } from './components/error/error-page/error-page.component';
import { NetworkAvailabilityComponent } from './components/modules/dashboard/network-availability/network-availability.component';
import { BscComponent } from './components/modules/kpi/bsc/bsc.component';
import { RncComponent } from './components/modules/kpi/rnc/rnc.component';
import { LteComponent } from './components/modules/kpi/lte/lte.component';

const appRoutes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'home', component: DashboardComponent },
  { path: 'networkstatus', component: NetworkStatusComponent },
  { path: 'active', component: ActiveComponent },
  { path: 'in-active', component: InActiveComponent },
  { path: 'halted', component: HaltedComponent },
  { path: 'availability-status', component: NetworkAvailabilityComponent },
  { path: 'bsc', component: BscComponent},
  { path: 'rnc', component: RncComponent},
  { path: 'lte', component: LteComponent},

  { path: 'not-found', component: ErrorPageComponent, data: { message: 'Page not found!' } },
  { path: '**', redirectTo: '/not-found' }
];

@NgModule({
    imports: [
      // RouterModule.forRoot(appRoutes, {useHash: true})
      RouterModule.forRoot(appRoutes)
    ],
    exports: [RouterModule]
  })

export class AppRoutingModule {

// tslint:disable-next-line:eofline
}