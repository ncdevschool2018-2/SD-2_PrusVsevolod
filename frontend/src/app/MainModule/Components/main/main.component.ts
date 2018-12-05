import {Component, OnInit, TemplateRef} from '@angular/core';
import {SubscriptionService} from "../../../services/subscription.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {SubscriptionModel} from "../../models/subscriptionModel";
import {BsModalRef, BsModalService, PageChangedEvent} from "ngx-bootstrap";
import {BasketItemService} from "../../../services/basketItem.service";
import {BasketItem} from "../../models/basketItem";
import {Category} from "../../models/category";
import {CategoryService} from "../../../services/category.service";
import {CustomerService} from "../../../services/customer.service";
import {WrapperList} from "../../models/wrapperList";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit{

  shoppingList: BasketItem[] = [];
  subs: SubscriptionModel[];
  bsModalRef: BsModalRef;
  value: number[] = [];
  size: number = 12;
  totalElements: number;
  itemsCounter: number;
  categories: Category[] = [];
  searchValue: string;
  currentCategoryId: string;
  loadOption: number = 1;

  constructor(private loadingService: Ng4LoadingSpinnerService, private subscriptionsService: SubscriptionService, private sbService: BasketItemService, private modalService: BsModalService, private categoryService: CategoryService, private customerService: CustomerService) {
  }

  // Calls on component init
  ngOnInit() {
    this.loadSubscriptions(0);
    this.loadCategories();
    if (localStorage.getItem('currentUserRole') == 'customer') {
      this.updateCustomerStatus();
      this.updateItemsCounter();
    }

  }

  private updateCustomerStatus():void{
    this.customerService.getCustomerByUserId().subscribe(customer =>{
      localStorage.setItem('status', customer.status.name);
    })
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
      this.subs = source.content;
      this.totalElements = source.totalElements;
      this.loadingService.hide();
    });
  }

  pageChanged(event: PageChangedEvent): void {

    switch (this.loadOption) {
      case 1: {
        this.loadSubscriptions(event.page - 1);
        break;
      }
      case 2: {
        this.loadSubscriptionsByNameLike(event.page - 1);
        break;
      }
      case 3: {
        this.loadSubscriptionsByCategoryId(event.page - 1);
        break;
      }
    }
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

    this.sbService.saveSb(new WrapperList<BasketItem>(this.shoppingList)).subscribe(() => {
      this.updateItemsCounter();
    });
    this.value = [];

    this.loadingService.hide();
  }

  adminOrOwner(): boolean {
    return !(localStorage.getItem('currentUserRole') == 'customer');
  }

  cantAdd(): boolean {
    if (localStorage.getItem('wallet') == null || localStorage.getItem('status') == 'blocked') return true;
    for (let value of this.value) {
      if (value > 0 && value < 1000) return false;
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

  openConfirmModal(template: TemplateRef<any>) {
    this.bsModalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  search(searchValue: string) {
    this.searchValue = searchValue;
    this.loadOption = 2;
    this.loadSubscriptionsByNameLike(0);
  }

  loadSubscriptionsByNameLike(page: number): void {
    this.loadingService.show();
    this.subscriptionsService.getSubscriptionsByNameLike(this.searchValue, page, this.size).subscribe(source => {
      this.subs = source.content;
      this.totalElements = source.totalElements;
      this.loadingService.hide();
    })
  }

  loadCategories(): void {
    this.loadingService.show();
    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories;
      this.loadingService.hide();
    })
  }

  filter(id: string): void {
    this.currentCategoryId = id;
    this.loadOption = 3;
    this.loadSubscriptionsByCategoryId(0);
  }

  loadSubscriptionsByCategoryId(page: number): void {
    this.loadingService.show();
    this.subscriptionsService.getSubscriptionByCategoryId(this.currentCategoryId, page, this.size).subscribe(source => {
      this.subs = source.content;
      this.totalElements = source.totalElements;
      this.loadingService.hide();
    })
  }

  loadAll(): void {
    this.loadOption = 1;
    this.loadSubscriptions(0);
  }

  customerWalletIsPresent(): boolean {
    if (this.adminOrOwner()) return true;
    if (localStorage.getItem('wallet') == null) return true;//Считаем что пользователь ещё не вошел в систему полностью.
    return (localStorage.getItem('wallet') != 'unregistered');
  }


  customerIsNotBlocked(): boolean {
    if (this.adminOrOwner()) return true;
    if (localStorage.getItem('status') == null) return true;//Считаем что пользователь ещё не вошел в систему полностью.
    return localStorage.getItem('status') == 'valid';
  }

}
