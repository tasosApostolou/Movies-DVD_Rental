import { Routes } from '@angular/router'
import { CustomerRegistrationComponent } from './components/customer-registration/customer-registration.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { CustomerWelcomeComponent } from './components/customer-components/customer-welcome/customer-welcome.component';
import { authCustomerGuard } from './shared/guards/auth-customer.guard';

import { WelcomeComponent } from './components/welcome/welcome.component';
import { EmployeeRegistrationComponent } from './components/employee-registration/employee-registration.component';
import { CustomerDirectorsComponent } from './components/customer-components/customer-directors/customer-directors.component';
import { MoviesComponent } from './components/customer-components/movies/movies.component';
import { ActorsComponent } from './components/customer-components/actors/actors.component';
import { MovieBehaviorComponent } from './components/movie-behavior/movie-behavior.component';



export const routes: Routes = [

  { path: '', component: WelcomeComponent},
  {path:'customer-registration', component: CustomerRegistrationComponent},
  {path:'store-registration', component: EmployeeRegistrationComponent},
  { path: 'login', component: UserLoginComponent },
  {path:'customer-welcome', component: CustomerWelcomeComponent,canActivate:[authCustomerGuard]},
  {path:'customer-directors', component: CustomerDirectorsComponent,canActivate:[authCustomerGuard]},
  {path:'movies', component: MoviesComponent,canActivate:[authCustomerGuard]},
  {path:'actors', component: ActorsComponent,canActivate:[authCustomerGuard]},
  { path: 'movie-behavior', component: MovieBehaviorComponent,canActivate:[authCustomerGuard]},

];
