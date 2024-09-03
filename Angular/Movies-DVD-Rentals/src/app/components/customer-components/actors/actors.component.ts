import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Actor } from 'src/app/shared/interfaces/actor';
import { Movie } from 'src/app/shared/interfaces/movie';
import { ActorService } from 'src/app/shared/services/actor.service';
import { UserService } from 'src/app/shared/services/user.service';
import { CustomerDirectorsComponent } from "../../customer-components/customer-directors/customer-directors.component";
import { MoviesComponent } from "../../customer-components/movies/movies.component";
import { MovieService } from 'src/app/shared/services/movie.service';

@Component({
  selector: 'app-actors',
  standalone: true,
  imports: [CustomerDirectorsComponent, MoviesComponent],
  templateUrl: './actors.component.html',
  styleUrl: './actors.component.css'
})
export class ActorsComponent {

  userService = inject(UserService)
  actorService = inject(ActorService)
  movieService = inject(MovieService)
  user = this.userService.user;
  actorId:number
  actors:Actor[] = []
  movies:Movie[] = []
  // directors: Director[] 
router = inject(Router)
  ngOnInit(): void {
  // this.loadActors()
  this.loadMovies()
  
  }
  // loadActors(){
  // this.actorService.getAllActors().subscribe((data:Actor[]) => {
  //   this.actors = data
  
  // })
  // }
  loadMovies(){
    this.movieService.getAllMovies().subscribe((data:Movie[]) => {
      this.movies = data
      const allActors = data.flatMap(movie => movie.actors);
      this.actors = this.getDistinctActors(data)
  

    })
    }
    getDistinctActors(movies: Movie[]): Actor[] {
      //Flatten the actors arrays from all movies into a single array
      const allActors = movies.flatMap(movie => movie.actors);
      //  Remove duplicate actors by id
      const distinctActors = Array.from(new Set(allActors.map(actor => actor.id)))
        .map(id => allActors.find(actor => actor.id === id));
    
      return distinctActors;
    }
  seeMovies(id:number){
    const actorMovies = this.movies.filter(movie => movie.actors.some(actor => actor.id === id));
    this.movieService.setMovies(actorMovies);
    this.router.navigate(['/movie-behavior'], { state: { mode: 'actor' } });
  }
}
