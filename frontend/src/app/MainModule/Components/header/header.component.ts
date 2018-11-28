import {Component, EventEmitter, Input, Output, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {WalletModalComponent} from "./wallet/wallet.component";
import {AuthService} from "../../../services/auth.service";
import {User} from "../../models/user";
import {Router} from "@angular/router";
import {BasketItemService} from "../../../services/basketItem.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent{

  public bsModalRef: BsModalRef;
  currentUser: User;
  @Input() itemsCounter: number;
  @Output() onSearch = new EventEmitter<string>();
  searchValue: string = '';
  // categories: Category[] = [];

  constructor(private modalService: BsModalService, private authService: AuthService, private router: Router, private sbService: BasketItemService) {
  }

  openModalWithLogin(template: TemplateRef<any>) {
    this.bsModalRef = this.modalService.show(template);
  }

  openModalWithWallet() {
    this.bsModalRef = this.modalService.show(WalletModalComponent);
  }

  logout(){
    this.authService.logout();
    this.router.navigateByUrl('/');
  }

  closeLoginModal():void{
    this.sbService.getCount().subscribe(count => {
      this.itemsCounter = count;
    });
    this.bsModalRef.hide();
  }
  userIsPresent(): boolean{
    return (localStorage.getItem('currentUser') != null);
  }

  walletIsPresent(): boolean{
    if (localStorage.getItem('wallet')) {
      return true;
    }
    return false;
  }

  userRole(): string{
    return (localStorage.getItem('currentUserRole'));
  }

  accountInfo(): string{
    switch (this.userRole()) {
      case "customer":{
        return "customerAccountInfo/" + localStorage.getItem('customerId');
      }
      case "owner":{
        return "ownerAccountInfo/" + localStorage.getItem('ownerId');
      }
      default: {
        return "/404";
      }
    }
  }

  notMainPage():boolean{
    return this.router.url != '/';
  }

  search(): void{
    this.onSearch.emit(this.searchValue);
  }

  // ngOnInit(): void {
  //   this.loadCategories();
  // }

  // loadCategories():void{
  //   this.categoryService.getCategories().subscribe(categories =>{
  //     this.categories = categories as Category[];
  //   })
  // }
}
