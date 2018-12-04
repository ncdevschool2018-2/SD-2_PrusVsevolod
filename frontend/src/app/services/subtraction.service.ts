import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SubtractionService {

  constructor(private http: HttpClient) {
  }

  editThreshold(value: number): Observable<number> {
    return this.http.put<number>('/api/subtraction/threshold/' + value, 0);
  }

  getThreshold(): Observable<number> {
    return this.http.get<number>('/api/subtraction/threshold');
  }
}
