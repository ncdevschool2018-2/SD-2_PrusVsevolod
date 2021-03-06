import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../MainModule/models/customer";
import {Ba} from "../MainModule/models/ba";
import {Content} from "../MainModule/models/content";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) {
  }

  getCustomers(page: number, size: number): Observable<Content<Customer>> {
    return this.http.get<Content<Customer>>('/api/customers?page=' + page + '&size=' + size);
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
  addMoneyOnBa(money: number): Observable<Ba>{
    return this.http.put<Ba>('/api/customers/ba/' + money, '');
  }
  getCustomerByUserId(): Observable<Customer>{
    return this.http.get<Customer>('/api/customers/user/');
  }
}
