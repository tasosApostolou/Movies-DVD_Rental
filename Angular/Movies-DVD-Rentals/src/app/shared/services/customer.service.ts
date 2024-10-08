import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { User } from '../interfaces/user';
import { UserService } from './user.service';
import { Customer, CustomerRegister } from '../interfaces/customer';

const API_URL = `${environment.apiURL}/customer`;

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  http: HttpClient = inject(HttpClient);
  userService = inject(UserService)
  personId = () => {
    return this.userService.user().roleEntityId
  }
  // personSignal = signal<Customer | null>(null);
  
    constructor() { }
  
    registerCustomer(customer:CustomerRegister){
      return this.http.post<{data:JSON}>(`http://localhost:8080/api/register/customer`, customer);
    }
    updateCustomer(customer:Customer){
      return this.http.put<{any:Customer}>(`${API_URL}/update/${
        customer.id}`,customer)
    }
    getCustomerById(customerId:number){
      return this.http.get<Customer>(`${API_URL}/${customerId}`,{
        headers: {
          Accept:'application/json'
        },
      })
    }
  
    deleteCustomer(){
      return this.http.delete<{user:User}>(`${environment.apiURL}/${this.userService.user().role.toLowerCase()}/${this.userService.user().roleEntityId}`)
    }
  }