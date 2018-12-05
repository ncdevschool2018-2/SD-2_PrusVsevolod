import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable()
export class RegistrationGuard implements CanActivate {


  constructor(private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    if ((localStorage.getItem('currentUserRole') == null)) {
      return true;
    } else {
      this.router.navigateByUrl('/404');
    }
    return false;
  }

}
