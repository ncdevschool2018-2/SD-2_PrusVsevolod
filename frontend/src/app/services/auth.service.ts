import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LoginUser} from "../MainModule/models/loginUser";
import {TokenStorage} from "./token.storage";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private token: TokenStorage) {
  }

  attemptAuth(loginUser: LoginUser): Observable<any> {
    // console.log('AttemptAuth ::');
    return this.http.post<any>('/api/token/generate-token', loginUser);
  }

  logout() {
    // remove user from local storage to log user out
    this.token.signOut();
    localStorage.clear();
    // localStorage.removeItem('currentUser');
    // if(localStorage.getItem('currentUserRole') == 'owner'){
    //   localStorage.removeItem('ownerId')
    // }
    // if(localStorage.getItem('currentUserRole') == 'customer'){
    //   localStorage.removeItem('customerId');
    // }
    // localStorage.removeItem('currentUserRole');
    // localStorage.removeItem('wallet');
  }

}

