import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Role} from "../modules/module/models/role";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) {
  }

  getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>('/api/roles');
  }
}
