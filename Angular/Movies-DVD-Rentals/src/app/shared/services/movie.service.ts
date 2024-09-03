import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { Category } from '../interfaces/category';
import { Movie } from '../interfaces/movie';
import { BehaviorSubject } from 'rxjs';

const API_URL = `${environment.apiURL}/movie`;

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  http: HttpClient = inject(HttpClient);
    constructor() { }
  
    getAllMovies(){
      return this.http.get<Movie[]>(`${API_URL}/get`,{
        headers: {
          Accept:'application/json'
        },
      })
    }
  
    // private directorMoviesSubject = new BehaviorSubject<Movie[]>([]);
    // directorMovies$ = this.directorMoviesSubject.asObservable();
  
    // setDirectorMovies(movies: Movie[]) {
    //   this.directorMoviesSubject.next(movies);
    // }

    // private actorMoviesSubject = new BehaviorSubject<Movie[]>([]);
    // actorMovies$ = this.actorMoviesSubject.asObservable();
  
    // setActorMovies(movies: Movie[]) {
    //   this.directorMoviesSubject.next(movies);
    // }

    private moviesSubject = new BehaviorSubject<Movie[]>([]);
    movies$ = this.moviesSubject.asObservable();
  
    setMovies(movies: Movie[]) {
      this.moviesSubject.next(movies);
    }
  }