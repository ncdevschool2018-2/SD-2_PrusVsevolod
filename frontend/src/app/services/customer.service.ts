import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../modules/module/models/customer";
import {Ba} from "../modules/module/models/ba";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) {
  }

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>('/api/customers');
  }

  saveEditedCustomer(customer: Customer): Observable<Customer>{
    return this.http.put<Customer>('/api/customers', customer);
  }

  saveCustomer(customer: Customer): Observable<Customer>{
    return this.http.post<Customer>('/api/customers', customer);
  }

  getCustomerById(id: string): Observable<Customer>{
    return this.http.get<Customer>('/api/customers/' + id);
  }

  deleteCustomer(id: string): Observable<void>{
    return this.http.delete<void>('/api/customers/' + id);
  }
  saveBa(ba: Ba): Observable<Ba>{
    return this.http.post<Ba>('/api/customers/ba', ba);
  }
  getCustomerByUserId(): Observable<Customer>{
    return this.http.get<Customer>('/api/customers/user/');
  }
}
