import { Component, inject } from '@angular/core';
import{ MatIconModule } from'@angular/material/icon'
import { Router, RouterLink } from '@angular/router';
import { MatMenu, MatMenuTrigger } from '@angular/material/menu';
import { MatButtonModule, MatIconButton } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import {MatListModule, MatNavList} from '@angular/material/list'; 
import {MatBadgeModule} from '@angular/material/badge';
import { MatButtonToggle, MatButtonToggleChange, MatButtonToggleGroup, MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatOption, MatSelect } from '@angular/material/select';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-navbar-home',
  standalone: true,
  imports: [MatIconModule,RouterLink,MatIconModule,MatMenu,MatMenuTrigger, MatButtonModule,MatIconModule,MatBadgeModule,MatToolbarModule,MatIconButton,MatSidenavModule,MatNavList,MatFormField,MatLabel,MatSelect,MatOption,MatMenu,MatMenuTrigger,MatButtonToggleModule,],
  templateUrl: './navbar-home.component.html',
  styleUrl: './navbar-home.component.css'
})
export class NavbarHomeComponent {
// userservice = inject(UserService)
// logout(){
//   this.userservice.logoutUser()
// }
}
