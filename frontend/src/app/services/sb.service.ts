import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sb} from "../modules/module/models/sb";

@Injectable({
  providedIn: 'root'
})
export class SbService {

  constructor(private http: HttpClient) {
  }

  saveSb(sb: Sb[]): Observable<Sb[]> {
    return this.http.post<Sb[]>('/api/sb', sb);
  }

  getSbByCustomerId(): Observable<Sb[]> {
    return this.http.get<Sb[]>('/api/sb/customer');
  }

  deleteSbById(id: string): Observable<Sb>{
    return this.http.delete<Sb>('/api/sb/' + id);
  }


}
