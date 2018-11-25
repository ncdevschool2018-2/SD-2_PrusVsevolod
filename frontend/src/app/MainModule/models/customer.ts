import {User} from "./user";
import {Ba} from "./ba";
import {Status} from "./status";

export class Customer {
  id: string;
  name: string;
  address: string;
  user: User = new User();
  ba: Ba;
  status: Status = new Status();

  constructor(){
    if (localStorage.getItem('wallet')){
      this.ba = new Ba();
    }
  }

  static cloneCustomer(customer: Customer): Customer{
    let cloneCustomer: Customer = new Customer();
    cloneCustomer.id = customer.id;
    cloneCustomer.name = customer.name;
    cloneCustomer.address = customer.address;
    cloneCustomer.user = customer.user;
    cloneCustomer.ba = customer.ba;
    cloneCustomer.user = User.cloneUser(customer.user);
    cloneCustomer.status = Status.cloneStatus(customer.status);
    return cloneCustomer;
  }
}
