import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Customer} from "../../../models/customer";
import {CustomerService} from "../../../../services/customer.service";

@Component({
  selector: 'modal-editCustomerMenu',
  templateUrl: './editCustomerModal.component.html',
  styleUrls: ['./editCustomerModal.component.css']
})
export class EditCustomerModalComponent {


  @Input() editableCustomer: Customer = new Customer();
  @Output() onChanged = new EventEmitter();

  constructor(private customersService: CustomerService) {
  }

  saveCustomer() {
    // console.log(this.editableOwner.name);
    this.customersService.saveEditedCustomer(this.editableCustomer).subscribe(() => {
      this.onChanged.emit();
    });
  }
}
