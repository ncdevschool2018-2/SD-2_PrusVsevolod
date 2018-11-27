import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BasketItem} from "../MainModule/models/basketItem";

@Injectable({
  providedIn: 'root'
})
export class BasketItemService {

  constructor(private http: HttpClient) {
  }

  saveSb(basketItem: BasketItem[]): Observable<void> {
    return this.http.post<void>('/api/basket_item', basketItem);
  }

  getSbByCustomerId(): Observable<BasketItem[]> {
    return this.http.get<BasketItem[]>('/api/basket_item/customer');
  }

  getCount(): Observable<number> {
    return this.http.get<number>('/api/basket_item/count');
  }

  deleteSbById(id: string): Observable<BasketItem>{
    return this.http.delete<BasketItem>('/api/basket_item/' + id);
  }

  deleteAllSbByCustomerId():Observable<void>{
    return this.http.delete<void>('/api/basket_item/customer');
  }
}
