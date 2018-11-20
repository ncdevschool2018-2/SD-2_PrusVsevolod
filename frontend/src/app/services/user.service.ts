import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../MainModule/models/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users');
  }

  saveUser(user: User): Observable<User>{
    return this.http.post<User>('/api/users', user);
  }

  getUserById(id: string): Observable<User>{
   return this.http.get<User>('/api/users/' + id);
  }

  getUser(): Observable<User>{
    return this.http.get<User>('/api/users/userLogin/');
  }

}
