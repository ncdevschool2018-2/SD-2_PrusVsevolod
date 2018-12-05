import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ActiveSubscription} from "../MainModule/models/activeSubscription";
import {WrapperList} from "../MainModule/models/wrapperList";

@Injectable({
  providedIn: 'root'
})
export class ActiveSubscriptionService {

  constructor(private http: HttpClient) {
  }

  saveAS(activeSubscriptions: WrapperList<ActiveSubscription>): Observable<ActiveSubscription[]>{
    return this.http.post<ActiveSubscription[]>('/api/active_subscription', activeSubscriptions);
  }

  getASByCustomerId(): Observable<ActiveSubscription[]>{
    return this.http.get<ActiveSubscription[]>('/api/active_subscription/customer');
  }

  deleteAS(id: string): Observable<ActiveSubscription>{
    return this.http.delete<ActiveSubscription>('/api/active_subscription/' + id);
  }
}
