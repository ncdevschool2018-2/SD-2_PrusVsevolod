import {Component, OnInit, TemplateRef} from '@angular/core';
import {SubscriptionService} from "../../../services/subscription.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {SubscriptionModel} from "../../models/subscriptionModel";
import {BsModalRef, BsModalService, PageChangedEvent} from "ngx-bootstrap";
import {BasketItemService} from "../../../services/basketItem.service";
import {BasketItem} from "../../models/basketItem";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  // public  content: Content;
  public shoppingList: BasketItem[] = [];
  public subs: SubscriptionModel[];
  public bsModalRef: BsModalRef;
  public value: number[] = [];
  public size: number = 12;
  public totalElements: number;
  itemsCounter: number;

  constructor(private loadingService: Ng4LoadingSpinnerService, private subscriptionsService: SubscriptionService, private sbService: BasketItemService, private modalService: BsModalService) {
  }

  // Calls on component init
  ngOnInit() {
    this.loadSubscriptions(0);
    if (localStorage.getItem('currentUserRole') == 'customer') {
      this.updateItemsCounter();
    }
  }

  private updateItemsCounter(): void {
    this.sbService.getCount().subscribe(count => {
      this.itemsCounter = count;
    });
  }

  private loadSubscriptions(page: number): void {
    this.loadingService.show();
    this.subscriptionsService.getSubscriptionsPaged(page, this.size).subscribe(source => {
      // Parse json response into local array
      this.subs = source.content as SubscriptionModel[];
      this.totalElements = source.totalElements;
      this.loadingService.hide();
    });
  }

  pageChanged(event: PageChangedEvent): void {
    this.loadSubscriptions(event.page - 1);
    this.value = [];
  }

  addToSb(): void {
    this.loadingService.show();
    let i: number = 0;
    for (let sub of this.subs) {
      if (this.value[i] > 0) {
        this.shoppingList.push(new BasketItem(localStorage.getItem('customerId'), sub, this.value[i]));
      }
      i++;
    }

    this.sbService.saveSb(this.shoppingList).subscribe(() => {
      this.updateItemsCounter();
    });
    this.value = [];

    this.loadingService.hide();
  }

  adminOrOwner(): boolean {
    return (localStorage.getItem('currentUserRole') == 'admin' || localStorage.getItem('currentUserRole') == 'owner');
  }

  cantAdd(): boolean {
    if (localStorage.getItem('wallet') == null) return true;
    for (let value of this.value) {
      if (value > 0) return false;
    }
    return true;
  }

  confirm() {
    this.addToSb();
    this.bsModalRef.hide();
  }

  decline() {
    this.bsModalRef.hide();
  }

  openConfirmModal(template: TemplateRef<any>){
      this.bsModalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  search(searchValue: string){
    this.loadingService.show();
    this.subscriptionsService.getSubscriptionsByNameLike(searchValue, 0, this.size).subscribe(source =>{
      this.subs = source.content as SubscriptionModel[];
      this.totalElements = source.totalElements;
      this.loadingService.hide();
    })
  }
}
