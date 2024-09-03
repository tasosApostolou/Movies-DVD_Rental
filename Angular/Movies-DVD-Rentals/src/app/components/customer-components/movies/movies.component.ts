import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/shared/interfaces/customer';
import { Director } from 'src/app/shared/interfaces/director';
import { Movie } from 'src/app/shared/interfaces/movie';
import { CustomerService } from 'src/app/shared/services/customer.service';
import { MovieService } from 'src/app/shared/services/movie.service';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-movies',
  standalone: true,
  imports: [],
  templateUrl: './movies.component.html',
  styleUrl: './movies.component.css'
})
export class MoviesComponent implements OnInit {
  userService = inject(UserService)
  movieService = inject(MovieService)
  router = inject(Router)
  user = this.userService.user;
  movies:Movie[] = []
  mode: 'director' | 'actor';
  ngOnInit(): void {
  this.loadMovies()
  }
  loadMovies(){
  this.movieService.getAllMovies().subscribe((data:Movie[]) => {
    this.movies = data
  })
  }
}