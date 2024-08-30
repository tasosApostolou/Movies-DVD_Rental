import { Routes } from '@angular/router'
import { CustomerRegistrationComponent } from './components/customer-registration/customer-registration.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { CustomerWelcomeComponent } from './components/customer-components/customer-welcome/customer-welcome.component';
import { authCustomerGuard } from './shared/guards/auth-customer.guard';

import { WelcomeComponent } from './components/welcome/welcome.component';
import { EmployeeRegistrationComponent } from './components/employee-registration/employee-registration.component';



export const routes: Routes = [

  { path: '', component: WelcomeComponent},
  {path:'customer-registration', component: CustomerRegistrationComponent},
  {path:'store-registration', component: EmployeeRegistrationComponent},
  { path: 'login', component: UserLoginComponent },
  {path:'customer-welcome', component: CustomerWelcomeComponent,canActivate:[authCustomerGuard]},
];
