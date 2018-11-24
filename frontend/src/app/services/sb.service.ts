import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sb} from "../MainModule/models/sb";

@Injectable({
  providedIn: 'root'
})
export class SbService {

  constructor(private http: HttpClient) {
  }

  saveSb(sb: Sb[]): Observable<void> {
    return this.http.post<void>('/api/sb', sb);
  }

  getSbByCustomerId(): Observable<Sb[]> {
    return this.http.get<Sb[]>('/api/sb/customer');
  }

  getCount(): Observable<number> {
    return this.http.get<number>('/api/sb/count');
  }

  deleteSbById(id: string): Observable<Sb>{
    return this.http.delete<Sb>('/api/sb/' + id);
  }

  deleteAllSbByCustomerId():Observable<void>{
    return this.http.delete<void>('/api/sb/customer');
  }
}
