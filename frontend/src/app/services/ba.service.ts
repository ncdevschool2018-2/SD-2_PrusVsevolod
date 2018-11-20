import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ba} from "../MainModule/models/ba";

@Injectable({
  providedIn: 'root'
})
export class BaService {

  constructor(private http: HttpClient) {
  }

  getBa(): Observable<Ba[]> {
    return this.http.get<Ba[]>('/api/billing_accounts');
  }
  saveEditedBa(ba: Ba): Observable<Ba>{
    return this.http.put<Ba>('/api/billing_accounts', ba);
  }
  // saveBa(ba: Ba): Observable<Ba>{
  //   return this.http.post<Ba>('/api/billing_accounts', ba);
  // }
}
