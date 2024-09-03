import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Movie } from 'src/app/shared/interfaces/movie';
import { MovieService } from 'src/app/shared/services/movie.service';

@Component({
  selector: 'app-movie-behavior',
  standalone: true,
  imports: [],
  templateUrl: './movie-behavior.component.html',
  styleUrl: './movie-behavior.component.css'
})
export class MovieBehaviorComponent {

//   movies: Movie[];

//   constructor(private movieService: MovieService) {
//     this.movieService.movies$.subscribe(movies => {
//       this.movies = movies;
//     });
//   }
// }
movies: Movie[] = [];
  mode: 'director' | 'actor';

  constructor(private router: Router, private movieService: MovieService) {}

  ngOnInit() {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.mode = navigation.extras.state['mode'];
    }

    this.movieService.movies$.subscribe(movies => {
      this.movies = movies;
    });

    // Optionally, set the title or any other dynamic content based on `mode`
  }
}