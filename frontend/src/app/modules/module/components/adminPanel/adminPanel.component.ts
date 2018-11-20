import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";
import {UserService} from "../../../../services/user.service";
import {Customer} from "../../models/customer";
import {Owner} from "../../models/owner";
import {CustomerService} from "../../../../services/customer.service";
import {OwnerService} from "../../../../services/owner.service";
import {User} from "../../models/user";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {EditOwnerModalComponent} from "../editOwnerModal/editOwnerModal.component";
import {EditCustomerModalComponent} from "../editCustomerModal/editCustomerModal.component";

@Component({
  selector: 'app-adminPanel',
  templateUrl: './adminPanel.component.html',
  styleUrls: ['./adminPanel.component.css']
})
export class AdminPanelComponent implements OnInit, OnDestroy {

  public bsModalRef: BsModalRef
  isCollapsed = false;

  public users: User[] = [];
  public customers: Customer[] = [];
  public owners: Owner[] = [];

  private subUsers: Subscription[] = [];

  constructor(private loadingService: Ng4LoadingSpinnerService, private usersService: UserService, private customersService: CustomerService, private ownersService: OwnerService, private modalService: BsModalService) {
  }

  // Calls on component init
  ngOnInit(){
    this.loadUsers();
    this.loadCustomers();
    this.loadOwners();
  }

  private loadUsers(): void {
    this.loadingService.show();
    this.subUsers.push(this.usersService.getUsers().subscribe(user => {
      // Parse json response into local array
      this.users = user as User[];
      this.loadingService.hide();
    }));
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

  openOwnerEditModal(owner: Owner) {
    const initialState = {
      editableOwner: Owner.cloneOwner(owner)
    };
    this.bsModalRef = this.modalService.show(EditOwnerModalComponent, {initialState, class: 'modal-lg'});
  }
  openCustomerEditModal(customer: Customer) {
    const initialState = {
      editableCustomer: Customer.cloneCustomer(customer)
    };
    this.bsModalRef = this.modalService.show(EditCustomerModalComponent, {initialState, class: 'modal-lg'});
  }

  ngOnDestroy(): void {
    this.subUsers.forEach(user =>{
      user.unsubscribe();
    })
  }

}
