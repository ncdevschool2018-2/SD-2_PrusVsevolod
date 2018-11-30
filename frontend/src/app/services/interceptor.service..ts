import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from "rxjs";
import {TokenStorage} from "./token.storage";
import {catchError} from "rxjs/operators";
import {Router} from "@angular/router";
import {AuthService} from "./auth.service";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenStorage, private router: Router, private authService: AuthService) {
  }

  //And with this in place, the JWT that was initially created on the Authentication server, is now being sent with each request to the Application server.
  //Перехватчик, перехватывает любые http запросы и как-то взаимодействует с ними, в моем случае если есть токен он добавляет этот токен в хидер, если нет, то перебрасывает на страницу логина.
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    if (this.token.getToken() != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())});
      return next.handle(authReq).pipe(
        catchError(err => {
            if (err.status === 401) {
              this.authService.logout();
              this.router.navigateByUrl('/');
            }

            // if (err.status === 403){
            //   this.router.navigateByUrl('/');
            // }

            const error = err.error.message || err.statusText;
            return throwError(error);
          }
        ));
    }
    return next.handle(authReq).pipe(
    catchError(err => {
          if (err.status === 401) {
            this.authService.logout();
            this.router.navigateByUrl('/');
          }

          // if (err.status === 403){
          //   this.router.navigateByUrl('/');
          // }

          const error = err.error.message || err.statusText;
          return throwError(error);
      }
    ));
  }

}
