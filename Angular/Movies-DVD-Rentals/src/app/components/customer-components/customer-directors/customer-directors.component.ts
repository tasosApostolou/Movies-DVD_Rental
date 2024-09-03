import { Component, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { Director } from 'src/app/shared/interfaces/director';
import { Movie } from 'src/app/shared/interfaces/movie';
import { MovieService } from 'src/app/shared/services/movie.service';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-customer-directors',
  standalone: true,
  imports: [],
  templateUrl: './customer-directors.component.html',
  styleUrl: './customer-directors.component.css'
})
export class CustomerDirectorsComponent {
  userService = inject(UserService)
  movieService = inject(MovieService)
  router = inject(Router)
  user = this.userService.user;
  movies:Movie[] = []
  directors: Set<Director> = new Set<Director>();
  // directors: Director[] 

  ngOnInit(): void {
  this.loadMovies()
  }
  loadMovies(){
  this.movieService.getAllMovies().subscribe((data:Movie[]) => {
    this.movies = data
    data.forEach(movie => {
      const director = movie.director
      if (![...this.directors].some(d => d.id === director.id)) {
        this.directors.add(director);
      }
    })
  })
  }
  seeMovies(id:number){
    const directorMovies = this.movies.filter(movie => 
      movie.director.id === id
    )
    this.movieService.setMovies(directorMovies);
    this.router.navigate(['/movie-behavior'], { state: { mode: 'director' } });
  }
}


