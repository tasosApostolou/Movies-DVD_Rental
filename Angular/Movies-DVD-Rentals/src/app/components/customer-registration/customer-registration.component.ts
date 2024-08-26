import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { UserService } from 'src/app/shared/services/user.service';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatMenu } from '@angular/material/menu';
import { MatToolbar, MatToolbarModule, MatToolbarRow } from '@angular/material/toolbar';
import { CustomerService } from 'src/app/shared/services/customer.service';
import { CustomerRegister } from 'src/app/shared/interfaces/customer';

@Component({
  selector: 'app-customer-registration',
  standalone: true,
  imports: [ReactiveFormsModule,MatFormFieldModule,MatInputModule,MatSelectModule,MatButtonModule,RouterLink,RouterOutlet,MatIconModule,MatMenu, MatToolbar, MatToolbarRow,MatToolbarModule,MatButtonModule, MatIconModule],
  templateUrl: './customer-registration.component.html',
  styleUrl: './customer-registration.component.css'
})
export class CustomerRegistrationComponent {
  userService = inject(UserService);
  customerService = inject(CustomerService);
  router:Router = 
  inject(Router)
  registrationStatus:{succes:boolean;message:string} = {succes:false,message:'Not Attempted yet'}
  
  form = new FormGroup({
    
    username:new FormControl('', Validators.required),
    password: new FormControl('',[Validators.required, Validators.minLength(4)]),
    firstname:new FormControl('', Validators.required),
    lastname: new FormControl('', Validators.required),
    role:new FormControl({value:'CUSTOMER',disabled:true},Validators.required),
    confirmPassword:new FormControl('',[Validators.required, Validators.minLength(4)]),
  },this.passwordConfirmsValidator)
  
  passwordConfirmsValidator(form:FormGroup){
    if (form.get('password').value !== form.get('confirmPassword').value){
      form.get('confirmPassword').setErrors({passwordMismatch:true});
      return{ passwordMismatch:true}
    }
    return {}
  }
  
  onSubmit(value:any){
    console.log(value);
    const CustomerUser = this.form.value as CustomerRegister
    delete CustomerUser['confirmPassword']
  
    this.customerService.registerCustomer(CustomerUser).subscribe({
      next: (response) => {
        console.log('User register', response);
        this.registrationStatus = {succes:true,message:"User register succesfully"}
      },
      error: (response) => {
        const message = "username already exists or error"
        console.log('Error registration user', message);
        this.registrationStatus = {succes:false,message}
        // this.registrationStatus = {succes:false,message:message}
      }
    })
  }
  registerUser(){
  this.form.reset();
  this.form.patchValue({role:"CUSTOMER"})
  this.registrationStatus ={
    succes:false,message:'Not Attempted yet'
  }
  }
  
  navigateLogin(){
    this.router.navigate(['login']);
  }
  
  }
  