import {Component, DoCheck, OnChanges, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Owner} from "../../models/owner";
import {OwnerService} from "../../../../services/owner.service";
import {User} from "../../models/user";
import {SubscriptionService} from "../../../../services/subscription.service";
import {SubscriptionModel} from "../../models/subscriptionModel";
import {EditSubscriptionModalComponent} from "../editSubscriptionModal/editSubscriptionModal.component";
import {initialState} from "ngx-bootstrap/timepicker/reducer/timepicker.reducer";
import {Customer} from "../../models/customer";
import {BaService} from "../../../../services/ba.service";

@Component({
  selector: 'app-ownerAccountInfo',
  templateUrl: './ownerAccountInfo.component.html',
  styleUrls: ['./ownerAccountInfo.component.css']
})
export class OwnerAccountInfoComponent implements OnInit{

  id: string;
  modalRef: BsModalRef;
  amount: number = 0;
  public owner: Owner = new Owner();
  public subscriptions: SubscriptionModel[] = [];
  private subOwner: Subscription[] = [];
  private subs: Subscription[] = [];

  constructor(private modalService: BsModalService, private activateRoute: ActivatedRoute, private loadingService: Ng4LoadingSpinnerService, private ownersService: OwnerService, private subscriptionsService: SubscriptionService, private baService: BaService) {
    this.id = activateRoute.snapshot.params['id'];
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  // Calls on component init
  ngOnInit() {
    this.loadingService.show();
    this.loadOwner();
    this.loadingService.hide();
  }

  private loadOwner(): void {
    this.owner.user = new User();
    this.subOwner.push(this.ownersService.getOwnerByUserId().subscribe(owner => {
      // Parse json response into local array
      this.owner = owner as Owner;
      this.loadSubscriptions();
    }));
  }

  private loadSubscriptions(): void {
    this.subs.push(this.subscriptionsService.getSubscriptionsByOwnerId(this.owner.id).subscribe(subscriptions => {
        this.subscriptions = subscriptions as SubscriptionModel[];
      }
    ))
  }

  walletIsPresent(): boolean{
    if (localStorage.getItem('wallet')) {
      // console.log(localStorage.getItem('wallet'));
      return true;
    }
    return false;
  }

  openAddSubscriptionModal():void{
    this.modalRef = this.modalService.show(EditSubscriptionModalComponent,{class: 'modal-lg'});
  }
  openEditSubscriptionModal(subscription: SubscriptionModel):void{
    const initialState = {
      editableSubscription: SubscriptionModel.cloneSubscription(subscription)
    };
    this.modalRef = this.modalService.show(EditSubscriptionModalComponent,{initialState, class: 'modal-lg'});
  }

  deleteSubscription(id: string):void{
    this.subscriptionsService.deleteSubscription(id).subscribe(()=>{
      this.loadSubscriptions();
    })
  }

  fillUp():void{
    this.owner.ba.balance += this.amount;
    this.baService.saveEditedBa(this.owner.ba).subscribe(()=>{
      this.loadOwner();
      this.modalRef.hide()
    });
  }
  amountIsNegative(): boolean{
    return this.amount < 0;
  }
}
