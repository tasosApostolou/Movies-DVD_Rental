import { Component } from '@angular/core';


import { RouterLink, RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./components/navbar/navbar/navbar.component";
import { NavbarHomeComponent } from "./components/navbar/navbar-home/navbar-home.component";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterLink, RouterOutlet, NavbarComponent, NavbarHomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {}
