import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";
import {Customer} from "../../models/customer";
import {Owner} from "../../models/owner";
import {CustomerService} from "../../../services/customer.service";
import {OwnerService} from "../../../services/owner.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-adminPanel',
  templateUrl: './adminPanel.component.html',
  styleUrls: ['./adminPanel.component.css']
})
export class AdminPanelComponent implements OnInit, OnDestroy {

  public bsModalRef: BsModalRef;
  isCollapsed = false;

  public customers: Customer[] = [];
  public owners: Owner[] = [];
  public editableOwner: Owner;
  public editableCustomer: Customer;
  private subUsers: Subscription[] = [];

  constructor(private loadingService: Ng4LoadingSpinnerService, private customersService: CustomerService, private ownersService: OwnerService, private modalService: BsModalService) {
  }

  // Calls on component init
  ngOnInit(){
    this.loadCustomers();
    this.loadOwners();
  }


  private loadCustomers(): void {
    this.loadingService.show();
    this.subUsers.push(this.customersService.getCustomers().subscribe(customer => {
      // Parse json response into local array
      this.customers = customer as Customer[];
      this.loadingService.hide();
    }));
  }

  private loadOwners(): void {
    this.loadingService.show();
    this.subUsers.push(this.ownersService.getOwners().subscribe(owner => {
      this.owners = owner as Owner[];
      this.loadingService.hide();
    }))
  }

  public deleteOwner(id: string): void {
    this.subUsers.push(this.ownersService.deleteOwner(id).subscribe(() => {
      this.loadOwners();
    }));
  }

  public deleteCustomer(id: string): void {
    this.subUsers.push(this.customersService.deleteCustomer(id).subscribe(() => {
      this.loadCustomers();
    }));
  }

  openOwnerEditModal(template: TemplateRef<any>, owner: Owner) {
    this.editableOwner = Owner.cloneOwner(owner);
    this.bsModalRef = this.modalService.show(template, {class:'modal-lg'});
  }

  closeEditOwnerModal():void{
    this.loadOwners();
    this.bsModalRef.hide();
  }
  openCustomerEditModal(template: TemplateRef<any>, customer: Customer) {
    this.editableCustomer = Customer.cloneCustomer(customer);
    this.bsModalRef = this.modalService.show(template, { class: 'modal-lg'});
  }
  closeEditCustomerModal():void{
    this.loadCustomers();
    this.bsModalRef.hide();
  }

  ngOnDestroy(): void {
    this.subUsers.forEach(user =>{
      user.unsubscribe();
    })
  }

}
