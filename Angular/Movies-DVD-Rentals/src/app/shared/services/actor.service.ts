import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { Actor } from '../interfaces/actor';

const API_URL = `${environment.apiURL}/actor`;

@Injectable({
  providedIn: 'root'
})
export class ActorService {

  http: HttpClient = inject(HttpClient);
    constructor() { }
  
    getAllActors(){
      return this.http.get<Actor[]>(`${API_URL}/get`,{
        headers: {
          Accept:'application/json'
        },
      })
    }


  

  }