import {Component} from '@angular/core';
import {BsModalRef} from "ngx-bootstrap";
import {LoginUser} from "../../../models/loginUser";
import {Router} from "@angular/router";
import {AuthService} from "../../../../services/auth.service";
import {TokenStorage} from "../../../../services/token.storage";
import {UserService} from "../../../../services/user.service";
import {OwnerService} from "../../../../services/owner.service";
import {CustomerService} from "../../../../services/customer.service";

@Component({
  selector: 'modal-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginModalComponent {

  isNotHidden = false;

  loginUser: LoginUser = new LoginUser();

  constructor(public bsModalRef: BsModalRef, private router: Router, private authService: AuthService, private token: TokenStorage, private userService: UserService,
              private ownersService: OwnerService, private customersService: CustomerService) {
  }

  login(): void {

    this.authService.attemptAuth(this.loginUser).subscribe(
      data => {
        this.token.saveToken(data.token);
        localStorage.setItem('currentUser', this.loginUser.login);

        this.userService.getUser().subscribe(user => {
          localStorage.setItem('currentUserRole', user.role.name);

          if (user.role.name == 'owner') {
            this.ownersService.getOwnerByUserId().subscribe(owner => {
              // console.log(owner);
              localStorage.setItem('ownerId', owner.id);
              if (owner.ba) {
                localStorage.setItem('wallet', owner.ba.id);
              }
            });
          }

          if (user.role.name == 'customer') {
            this.customersService.getCustomerByUserId().subscribe(customer => {
              localStorage.setItem('customerId', customer.id);
              if (customer.ba) {
                localStorage.setItem('wallet', customer.ba.id);
              }
            });
          }

        });
        this.bsModalRef.hide()
      },
      () => {
        this.isNotHidden = true;
      }
    );

  }


}
