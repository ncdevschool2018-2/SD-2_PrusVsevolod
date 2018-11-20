import {Component, OnInit} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {LoginModalComponent} from "../login/login.component";
import {WalletModalComponent} from "../wallet/wallet.component";
import {AuthService} from "../../../services/auth.service";
import {User} from "../../models/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent{

  public bsModalRef: BsModalRef;
  currentUser: User;

  constructor(private modalService: BsModalService, private authService: AuthService, private router: Router) {
  }

  openModalWithLogin() {
    this.bsModalRef = this.modalService.show(LoginModalComponent);
  }

  openModalWithWallet() {
    this.bsModalRef = this.modalService.show(WalletModalComponent);
  }

  logout(){
    this.authService.logout();
    this.router.navigateByUrl('/');
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
}
