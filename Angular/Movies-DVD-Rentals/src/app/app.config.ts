import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
 
import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
 
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { AuthInterceptor } from './auth.interceptor';
 
export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes), provideAnimationsAsync(),
    provideHttpClient(withInterceptorsFromDi()), provideAnimationsAsync(),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
  },
  ]
};
 