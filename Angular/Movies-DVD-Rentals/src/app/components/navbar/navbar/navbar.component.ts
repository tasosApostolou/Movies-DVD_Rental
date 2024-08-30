import { Component, inject } from '@angular/core';
import { UserService } from 'src/app/shared/services/user.service';
import { NavbarHomeComponent } from "../navbar-home/navbar-home.component";
import { NavbarCustomerComponent } from "../navbar-customer/navbar-customer.component";
import { NavbarEmployeeComponent } from "../navbar-employee/navbar-employee.component";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [NavbarHomeComponent, NavbarCustomerComponent, NavbarEmployeeComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  userService = inject(UserService)
  user = this.userService.user
}
