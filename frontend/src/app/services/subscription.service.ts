import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubscriptionModel} from "../MainModule/models/subscriptionModel";
import {Content} from "../MainModule/models/content";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  constructor(private http: HttpClient) {
  }

  getSubscriptionsPaged(page: number, size: number): Observable<Content<SubscriptionModel>> {
    return this.http.get<Content<SubscriptionModel>>('/api/subscriptions?page=' + page + '&size=' + size);
  }
  getSubscriptionsByOwnerId(id: string): Observable<SubscriptionModel[]>{
    return this.http.get<SubscriptionModel[]>('/api/subscriptions/owner/' + id);
  }
  saveSubscription(subscription: SubscriptionModel): Observable<SubscriptionModel>{
    return this.http.post<SubscriptionModel>('/api/subscriptions', subscription);
  }
  // saveEditedSubscription(subscription: SubscriptionModel): Observable<SubscriptionModel>{
  //   return this.http.put<SubscriptionModel>('/api/subscriptions', subscription);
  // }
  getSubscriptionsByNameLike(name: string, page: number, size: number): Observable<Content<SubscriptionModel>>{
    return this.http.get<Content<SubscriptionModel>>('/api/subscriptions/search?name=' + name + "&page=" + page + "&size=" + size);
  }
  deleteSubscription(id: string): Observable<void> {
    return this.http.delete<void>('/api/subscriptions/' + id);
  }
}
