import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar/navbar.component";
import { MatMenu, MatMenuTrigger } from '@angular/material/menu';
import { MatIcon } from '@angular/material/icon';
import { NavbarHomeComponent } from "../navbar/navbar-home/navbar-home.component";

@Component({
  selector: 'app-welcome',
  standalone: true,
  imports: [WelcomeComponent, NavbarComponent, MatMenuTrigger, MatIcon, MatMenu, NavbarHomeComponent],
  templateUrl: './welcome.component.html',
  styleUrl: './welcome.component.css'
})
export class WelcomeComponent {

}
