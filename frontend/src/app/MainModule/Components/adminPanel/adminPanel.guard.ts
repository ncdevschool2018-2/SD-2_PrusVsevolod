import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable()
export class AdminPanelGuard implements CanActivate{

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) : Observable<boolean> | boolean{
    return (localStorage.getItem('currentUserRole') == 'admin');
  }

}
