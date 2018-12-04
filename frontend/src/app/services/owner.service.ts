import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Owner} from "../MainModule/models/owner";
import {Ba} from "../MainModule/models/ba";
import {Customer} from "../MainModule/models/customer";
import {Content} from "../MainModule/models/content";

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  constructor(private http: HttpClient) {
  }

  getOwners(page: number, size: number): Observable<Content<Owner>> {
    return this.http.get<Content<Owner>>('/api/owners?page=' + page + '&size=' + size);
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
  // getBa(): Observable<Ba> {
  //   return this.http.get<Ba>('/api/owners/ba');
  // }

  getOwnerByUserId(): Observable<Customer> {
    return this.http.get<Customer>('/api/owners/user/');
  }
}
