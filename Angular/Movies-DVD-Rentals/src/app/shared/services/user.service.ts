import { HttpClient } from '@angular/common/http';
import { effect, inject, Injectable, signal } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.development';
import { LoggedInUser, Credentials, User } from '../interfaces/user';

const API_USER = `${environment.apiURL}/user`;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  http: HttpClient = inject(HttpClient);
  router: Router = inject(Router);
  user = signal<LoggedInUser | null>(null);
  id:number;
  
    constructor() {
      effect(() => {
        if (this.user()) {
          console.log('User loggedin', this.user().sub);
          this.id=this.user().userId
        } else {
          console.log('No user Logged In');
        }
      });
    }
  
    loginUser(credentials: Credentials) {
     return this.http.post<{jwt:string} >(`${environment.apiURL}/login`,credentials);
    }
  
    logoutUser(){
      this.user.set(null)
  localStorage.removeItem('access_token');
  this.router.navigate(['login']);
    }
  
    updateUser(user:User){
      return this.http.put<{user:User}>(`${API_USER}/update/${this.user().userId}`,user)
    }
  
    deleteUser(){
      return this.http.delete<{user:User}>(`${API_USER}/${this.user().userId}`)
    }
  }
