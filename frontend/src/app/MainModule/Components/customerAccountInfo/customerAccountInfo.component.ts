import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {CustomerService} from "../../../services/customer.service";
import {Customer} from "../../models/customer";
import {BaService} from "../../../services/ba.service";

@Component({
  selector: 'app-customerAccountInfo',
  templateUrl: './customerAccountInfo.component.html',
  styleUrls: ['./customerAccountInfo.component.css']
})
export class CustomerAccountInfoComponent implements OnInit{

  amount: number = 0;
  modalRef: BsModalRef;
  public customer: Customer = new Customer();
  private subCustomer: Subscription[] = [];
  constructor(private modalService: BsModalService, private loadingService: Ng4LoadingSpinnerService, private customersService: CustomerService, private baService: BaService) {
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  // Calls on component init
  ngOnInit() {
    this.loadCustomer();

  }

  private loadCustomer(): void {
    this.loadingService.show();
    this.subCustomer.push(this.customersService.getCustomerByUserId().subscribe(customer => {
      // Parse json response into local array
      this.customer = customer as Customer;
      this.loadingService.hide();
    }));
  }

  walletIsPresent(): boolean{
    if (localStorage.getItem('wallet')) {
      return true;
    }
    return false;
  }

  fillUp():void{
    this.customer.ba.balance += this.amount;
    // console.log(this.customer.ba.cvv);
    this.baService.saveEditedBa(this.customer.ba).subscribe(()=>{
      this.loadCustomer();
      this.modalRef.hide()
    });
  }

  amountIsNegative(): boolean{
    return this.amount < 0;
  }
}
