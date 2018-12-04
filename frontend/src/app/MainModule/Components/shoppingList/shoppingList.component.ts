import {Component, OnInit, TemplateRef} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BasketItem} from "../../models/basketItem";
import {BasketItemService} from "../../../services/basketItem.service";
import {ActiveSubscription} from "../../models/activeSubscription";
import {ActiveSubscriptionService} from "../../../services/activeSubscription.service";
import {Router} from "@angular/router";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-shoppingList',
  templateUrl: './shoppingList.component.html',
  styleUrls: ['./shoppingList.component.css']
})
export class ShoppingListComponent implements OnInit{

  shoppingBasket: BasketItem[] = [];
  private subscriptions: ActiveSubscription[] = [];
  total: number = 0;
  itemsCounter: number;
  bsModalRef: BsModalRef;

  constructor(private loadingService: Ng4LoadingSpinnerService, private sbService: BasketItemService, private activeSubscriptionService: ActiveSubscriptionService, private router: Router, private modalService: BsModalService) {
  }

  ngOnInit(): void {
    this.loadShoppingBasket();
  }

  private updateItemsCounter():void{
    this.sbService.getCount().subscribe(count => {
      this.itemsCounter = count;
    });
  }

  loadShoppingBasket(): void {
    this.loadingService.show();
    this.sbService.getSbByCustomerId().subscribe(shoppingBasket => {
      this.shoppingBasket = shoppingBasket as BasketItem[];
      this.updateItemsCounter();
      this.totalCount();
      this.loadingService.hide();
    });
  }

  totalCount(): void {
    this.total = 0;
    for (let shoppingItem of this.shoppingBasket) {
      this.total += shoppingItem.quantity * shoppingItem.subscription.price;
    }
  }

  deleteSbItem(id: string): void {
    this.sbService.deleteSbById(id).subscribe(() => {
      this.updateItemsCounter();
      this.loadShoppingBasket();
    });
  }

  checkout(): void {
    this.shoppingBasket.forEach(item => {
      this.subscriptions.push(new ActiveSubscription(item.subscription, item.quantity));
    });
    this.activeSubscriptionService.saveAS(this.subscriptions).subscribe(() => {
      this.subscriptions = [];
      this.sbService.deleteAllSbByCustomerId().subscribe(() => {
        // this.loadShoppingBasket();
        this.router.navigateByUrl('/');
      });
    });
  }

  confirm() {
    this.checkout();
    this.bsModalRef.hide();
  }

  decline() {
    this.bsModalRef.hide();
  }

  openConfirmModal(template: TemplateRef<any>){
    this.bsModalRef = this.modalService.show(template, {class: 'modal-sm'});
  }
}
