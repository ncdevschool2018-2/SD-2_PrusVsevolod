import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Owner} from "../modules/module/models/owner";
import {Ba} from "../modules/module/models/ba";
import {Customer} from "../modules/module/models/customer";

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  constructor(private http: HttpClient) {
  }

  getOwners(): Observable<Owner[]> {
    return this.http.get<Owner[]>('/api/owners');
  }

  saveEditedOwner(owner: Owner): Observable<Owner>{
    return this.http.put<Owner>('/api/owners', owner);
  }

  saveOwner(owner: Owner): Observable<Owner> {
    return this.http.post<Owner>('/api/owners', owner);
  }

  getOwnerById(id: string): Observable<Owner> {
    return this.http.get<Owner>('/api/owners/' + id);
  }

  deleteOwner(id: string): Observable<void> {
    return this.http.delete<void>('/api/owners/' + id);
  }

  saveBa(ba: Ba): Observable<Ba> {
    return this.http.post<Ba>('/api/owners/ba', ba);
  }

  getOwnerByUserId(): Observable<Customer> {
    return this.http.get<Customer>('/api/owners/user/');
  }
}
