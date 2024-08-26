import { Routes } from '@angular/router'
import { CustomerRegistrationComponent } from './components/customer-registration/customer-registration.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { CustomerWelcomeComponent } from './components/customer-components/customer-welcome/customer-welcome.component';
import { authCustomerGuard } from './shared/guards/auth-customer.guard';
import { AdminRegistrationComponent } from './components/admin-registration/admin-registration.component';
import { WelcomeComponent } from './components/welcome/welcome.component';



export const routes: Routes = [

  { path: '', component: WelcomeComponent},
  {path:'customer-registration', component: CustomerRegistrationComponent},
  {path:'store-registration', component: AdminRegistrationComponent},
  { path: 'login', component: UserLoginComponent },
  {path:'customer-welcome', component: CustomerWelcomeComponent,canActivate:[authCustomerGuard]},
];
