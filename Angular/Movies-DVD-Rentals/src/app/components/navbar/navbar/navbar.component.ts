import { Component, inject } from '@angular/core';
import { UserService } from 'src/app/shared/services/user.service';
import { NavbarHomeComponent } from "../navbar-home/navbar-home.component";
import { NavbarCustomerComponent } from "../navbar-customer/navbar-customer.component";
import { NavbarAdminComponent } from "../navbar-admin/navbar-admin.component";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [NavbarHomeComponent, NavbarCustomerComponent, NavbarAdminComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  userService = inject(UserService)
  user = this.userService.user
}
