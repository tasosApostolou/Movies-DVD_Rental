import { Component } from '@angular/core';


import { RouterLink, RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./components/navbar/navbar/navbar.component";
import { NavbarHomeComponent } from "./components/navbar/navbar-home/navbar-home.component";
import { WelcomeComponent } from "./components/welcome/welcome.component";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterLink, RouterOutlet, NavbarComponent, NavbarHomeComponent, WelcomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {}
