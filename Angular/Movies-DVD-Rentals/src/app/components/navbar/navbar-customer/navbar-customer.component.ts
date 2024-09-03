import { Component, inject } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { MatMenu, MatMenuTrigger } from '@angular/material/menu';
import{ MatIconModule } from'@angular/material/icon'
import { RouterLink } from '@angular/router';
import { MatButtonModule, MatIconButton } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import {MatListModule, MatNavList} from '@angular/material/list'; 
import {MatBadgeModule} from '@angular/material/badge';
import { MatButtonToggle, MatButtonToggleChange, MatButtonToggleGroup, MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatOption, MatSelect } from '@angular/material/select';
import { UserService } from 'src/app/shared/services/user.service';
import { MovieService } from 'src/app/shared/services/movie.service';
import { Director } from 'src/app/shared/interfaces/director';
import { Movie } from 'src/app/shared/interfaces/movie';

@Component({
  selector: 'app-navbar-customer',
  standalone: true,
  imports: [MatIconModule,RouterLink,MatIconModule,MatMenu,MatMenuTrigger, MatButtonModule,MatIconModule,MatBadgeModule,MatToolbarModule,MatIconButton,MatSidenavModule,MatNavList,MatFormField,MatLabel,MatSelect,MatOption,MatMenu,MatMenuTrigger,MatButtonToggleModule],
  templateUrl: './navbar-customer.component.html',
  styleUrl: './navbar-customer.component.css'
})
export class NavbarCustomerComponent {
userService = inject(UserService)
movieService = inject(MovieService)

logout(){
  this.userService.logoutUser()
}
}
