import {Component, OnInit, TemplateRef} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Customer} from "../../models/customer";
import {CustomerService} from "../../../services/customer.service";
import {BsModalRef, BsModalService, PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-adminPanel',
  templateUrl: './adminPanel.component.html',
  styleUrls: ['./adminPanel.component.css']
})
export class AdminPanelComponent implements OnInit {

  bsModalRef: BsModalRef;
  isCollapsed: boolean = false;

  customers: Customer[] = [];
  editableCustomer: Customer;
  size: number = 10;
  totalElements: number = 0;
  page: number = 0;

  // private subUsers: Subscription[] = [];

  constructor(private loadingService: Ng4LoadingSpinnerService, private customersService: CustomerService, private modalService: BsModalService) {
  }

  // Calls on component init
  ngOnInit() {
    this.loadCustomers(this.page, this.size);
  }

  private loadCustomers(page: number, size: number): void {
    this.loadingService.show();
    this.customersService.getCustomers(page, size).subscribe(source => {
      // Parse json response into local array
      this.customers = source.content as Customer[];
      this.totalElements = source.totalElements;
      this.loadingService.hide();
    });
  }

  public deleteCustomer(id: string): void {
    this.customersService.deleteCustomer(id).subscribe(() => {
      this.loadCustomers(this.page, this.size);
    });
  }

  openCustomerEditModal(template: TemplateRef<any>, customer: Customer) {
    this.editableCustomer = Customer.cloneCustomer(customer);
    this.bsModalRef = this.modalService.show(template, {class: 'modal-lg'});
  }

  closeEditCustomerModal(): void {
    this.loadCustomers(this.page, this.size);
    this.bsModalRef.hide();
  }

  confirmDeleteCustomer(id: string) {
    this.deleteCustomer(id);
    this.bsModalRef.hide();
  }

  decline() {
    this.bsModalRef.hide();
  }

  openConfirmModal(template: TemplateRef<any>) {
    this.bsModalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  pageChanged(event: PageChangedEvent): void {
    this.page = event.page -1;
    this.loadCustomers(this.page, this.size);
  }

  collapse(isCollapsed: boolean):void{
    this.isCollapsed = isCollapsed;
  }
}
