import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {CustomerService} from "../../../services/customer.service";
import {Customer} from "../../models/customer";
import {BaService} from "../../../services/ba.service";
import {ActiveSubscription} from "../../models/activeSubscription";
import {ActiveSubscriptionService} from "../../../services/activeSubscription.service";
import {BasketItemService} from "../../../services/basketItem.service";

@Component({
  selector: 'app-customerAccountInfo',
  templateUrl: './customerAccountInfo.component.html',
  styleUrls: ['./customerAccountInfo.component.css']
})
export class CustomerAccountInfoComponent implements OnInit{

  amount: number = 0;
  modalRef: BsModalRef;
  itemsCounter: number;
  public customer: Customer = new Customer();
  public activeSubs: ActiveSubscription[] = [];
  constructor(private modalService: BsModalService, private loadingService: Ng4LoadingSpinnerService, private sbService: BasketItemService, private customersService: CustomerService, private baService: BaService, private ASService: ActiveSubscriptionService) {
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  // Calls on component init
  ngOnInit() {
    this.loadCustomer();
    this.loadActiveSubs();
    this.updateItemsCounter();
  }

  private loadCustomer(): void {
    this.loadingService.show();
    this.customersService.getCustomerByUserId().subscribe(customer => {
      // Parse json response into local array
      this.customer = customer as Customer;
      this.loadingService.hide();
    });
  }

  private loadActiveSubs():void{
    this.loadingService.show();
    this.ASService.getASByCustomerId().subscribe(activeSubscriptions =>{
      this.activeSubs = activeSubscriptions as ActiveSubscription[];
      this.loadingService.hide();
    });

  }

  walletIsPresent(): boolean{
    if (localStorage.getItem('wallet')) {
      return true;
    }
    return false;
  }

  fillUp():void{
    this.customer.ba.balance += this.amount;
    this.baService.saveEditedBa(this.customer.ba).subscribe(()=>{
      this.loadCustomer();
      this.modalRef.hide()
    });
  }

  amountIsNegative(): boolean{
    return this.amount < 0;
  }

  delete(id: string):void{
    this.ASService.deleteAS(id).subscribe(()=>{
      this.loadActiveSubs();
    })
  }

  private updateItemsCounter(): void {
    this.sbService.getCount().subscribe(count => {
      this.itemsCounter = count;
    });
  }
}
