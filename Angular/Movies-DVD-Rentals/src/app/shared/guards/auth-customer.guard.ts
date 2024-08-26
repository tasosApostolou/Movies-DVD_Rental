import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { UserService } from '../services/user.service';

export const authCustomerGuard: CanActivateFn = (route, state) => {
    const userService = inject(UserService);
    const router = inject(Router)
  
    if(userService.user().role==="CUSTOMER"){
      return true
    }
    return router.navigate(['login']);
  };
  