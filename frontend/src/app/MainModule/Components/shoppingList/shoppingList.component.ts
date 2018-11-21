import {Component, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Sb} from "../../models/sb";
import {SbService} from "../../../services/sb.service";
import {ActiveSubscription} from "../../models/activeSubscription";
import {ActiveSubscriptionService} from "../../../services/activeSubscription.service";

@Component({
  selector: 'app-shoppingList',
  templateUrl: './shoppingList.component.html',
  styleUrls: ['./shoppingList.component.css']
})
export class ShoppingListComponent implements OnInit{

  public shoppingBasket: Sb[] = [];
  private subscriptions: ActiveSubscription[] = [];
  public total: number = 0;

  constructor(private loadingService: Ng4LoadingSpinnerService, private sbService: SbService, private activeSubscriptionService: ActiveSubscriptionService) {
  }

  ngOnInit(): void {
    this.loadShoppingBasket();
  }


  loadShoppingBasket(): void {
    this.loadingService.show();
    this.sbService.getSbByCustomerId().subscribe(shoppingBasket => {
      this.shoppingBasket = shoppingBasket as Sb[];
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
        this.loadShoppingBasket();
      });
    });
  }
}
