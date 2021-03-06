import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ActivatedRoute} from "@angular/router";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Owner} from "../../models/owner";
import {OwnerService} from "../../../services/owner.service";
import {User} from "../../models/user";
import {SubscriptionService} from "../../../services/subscription.service";
import {SubscriptionModel} from "../../models/subscriptionModel";
import {BaService} from "../../../services/ba.service";

@Component({
  selector: 'app-ownerAccountInfo',
  templateUrl: './ownerAccountInfo.component.html',
  styleUrls: ['./ownerAccountInfo.component.css']
})
export class OwnerAccountInfoComponent implements OnInit {

  id: string;
  modalRef: BsModalRef;
  amount: number = 0;
  owner: Owner = new Owner();
  subscriptions: SubscriptionModel[] = [];
  // private subOwner: Subscription[] = [];
  // private subs: Subscription[] = [];
  editableSubscription: SubscriptionModel;

  constructor(private modalService: BsModalService, private activateRoute: ActivatedRoute, private loadingService: Ng4LoadingSpinnerService, private ownersService: OwnerService, private subscriptionsService: SubscriptionService, private baService: BaService) {
    this.id = activateRoute.snapshot.params['id'];
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  // Calls on component init
  ngOnInit() {
    this.loadOwner();
  }

  private loadOwner(): void {
    this.loadingService.show();
    this.owner.user = new User();
    this.ownersService.getOwnerByUserId().subscribe(owner => {
      // Parse json response into local array
      this.owner = owner;
      this.loadSubscriptions();
    });
  }

  private loadSubscriptions(): void {
    this.subscriptionsService.getSubscriptionsByOwnerId(this.owner.id).subscribe(subscriptions => {
        this.subscriptions = subscriptions;
        this.loadingService.hide();
      }
    );
  }

  walletIsPresent(): boolean {
    if (localStorage.getItem('wallet') != 'unregistered') {
      return true;
    }
    return false;
  }

  openAddSubscriptionModal(template: TemplateRef<any>): void {
    this.editableSubscription = new SubscriptionModel();
    this.modalRef = this.modalService.show(template, {class: 'modal-lg'});
  }

  openEditSubscriptionModal(template: TemplateRef<any>, subscription: SubscriptionModel): void {
    this.editableSubscription = SubscriptionModel.cloneSubscription(subscription);
    this.modalRef = this.modalService.show(template, {class: 'modal-lg'});
  }

  closeEditSubscriptionModal(): void {
    this.loadSubscriptions();
    this.modalRef.hide();
  }

  deleteSubscription(id: string): void {
    this.subscriptionsService.deleteSubscription(id).subscribe(() => {
      this.loadSubscriptions();
    })
  }

  fillUp(): void {
    this.owner.ba.balance += this.amount;
    this.baService.saveEditedBa(this.owner.ba).subscribe(() => {
      this.loadOwner();
      this.amount = 0;
      this.modalRef.hide();
    });
  }

  amountIsNegative(): boolean {
    return this.amount < 0;
  }
}
