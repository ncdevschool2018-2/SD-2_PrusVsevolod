import {Component} from '@angular/core';
import {Subscription} from "rxjs";
import {BsModalRef} from "ngx-bootstrap";
import {Customer} from "../../models/customer";
import {CustomerService} from "../../../../services/customer.service";

@Component({
  selector: 'modal-editCustomerMenu',
  templateUrl: './editCustomerModal.component.html',
  styleUrls: ['./editCustomerModal.component.css']
})
export class EditCustomerModalComponent{

  public editableCustomer: Customer = new Customer();
  private subCustomer: Subscription[] = [];

  constructor(private customersService: CustomerService, public bsModalRef: BsModalRef){}

  saveCustomer(){
    // console.log(this.editableOwner.name);
    this.subCustomer.push(this.customersService.saveEditedCustomer(this.editableCustomer).subscribe(() => {
    }));
  }
}
