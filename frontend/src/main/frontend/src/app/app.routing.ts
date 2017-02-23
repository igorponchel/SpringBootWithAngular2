import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './auth/register/index';
import { DashboardComponent } from './dashboard/index';
import { LoginComponent } from './auth/login/index';
import { ParametrageComponent } from './parametrage/index';
import { TechnicienComponent } from './technicien/index';
import { AuthGuard } from './auth/index';

export const routes: Routes = [

  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    data: { title: 'Dashboard' },
    path: 'dashboard', 
    component: DashboardComponent    
  },
  { path: 'technicien', component: TechnicienComponent, canActivate: [AuthGuard], data: { title: 'Liste des techniciens' } },
  { path: 'parametrage', component: ParametrageComponent, canActivate: [AuthGuard], data: { title: 'Paramètrage' } },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
