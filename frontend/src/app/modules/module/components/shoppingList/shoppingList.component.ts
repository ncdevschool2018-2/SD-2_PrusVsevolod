import {Component, OnDestroy, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Sb} from "../../models/sb";
import {Subscription} from "rxjs";
import {SbService} from "../../../../services/sb.service";
@Component({
  selector: 'app-shoppingList',
  templateUrl: './shoppingList.component.html',
  styleUrls: ['./shoppingList.component.css']
})
export class ShoppingListComponent implements OnInit, OnDestroy{

  public shoppingBasket: Sb[] = [];
  private subShoppingBasket: Subscription[] = [];
  public total: number = 0;

  constructor(private loadingService: Ng4LoadingSpinnerService, private sbService: SbService) {
  }

  ngOnDestroy(): void {
    this.subShoppingBasket.forEach(shoppingItem =>{
      shoppingItem.unsubscribe();
    })
  }

  ngOnInit(): void {
    this.loadShoppingBasket();
  }


  loadShoppingBasket():void{
    this.loadingService.show();
    this.subShoppingBasket.push(this.sbService.getSbByCustomerId().subscribe(shoppingBasket =>{
      this.shoppingBasket = shoppingBasket as Sb[];
      this.totalCount();
      this.loadingService.hide();
    }));
  }

  totalCount():void{
    this.total = 0;
    for (let shoppingItem of this.shoppingBasket){
      this.total += shoppingItem.quantity*shoppingItem.subscription.price;
    }
  }

  deleteSbItem(id: string):void{
    this.sbService.deleteSbById(id).subscribe(()=>{
      this.loadShoppingBasket();
    });
  }
}
