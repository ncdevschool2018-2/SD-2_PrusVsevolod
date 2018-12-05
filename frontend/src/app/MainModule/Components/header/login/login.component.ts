import {Component, EventEmitter, Output} from '@angular/core';
import {LoginUser} from "../../../models/loginUser";
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
  @Output() onChange = new EventEmitter();

  loginUser: LoginUser = new LoginUser();

  constructor(private authService: AuthService, private token: TokenStorage, private userService: UserService,
              private ownersService: OwnerService, private customersService: CustomerService) {
  }

  login(): void {

    this.authService.attemptAuth(this.loginUser).subscribe(
      data => {
        this.token.saveToken(data.token);
        localStorage.setItem('currentUser', this.loginUser.login);

        this.userService.getUser().subscribe(user => {
          localStorage.setItem('currentUserRole', user.role.name);
          this.onChange.emit();

          if (user.role.name == 'owner') {
            this.ownersService.getOwnerByUserId().subscribe(owner => {
              localStorage.setItem('ownerId', owner.id);
              if (owner.ba) {
                localStorage.setItem('wallet', owner.ba.id);
              } else localStorage.setItem('wallet', 'unregistered');
            });
          }

          if (user.role.name == 'customer') {
            this.customersService.getCustomerByUserId().subscribe(customer => {
              localStorage.setItem('customerId', customer.id);
              localStorage.setItem('status', customer.status.name);
              if (customer.ba) {
                localStorage.setItem('wallet', customer.ba.id);
              } else localStorage.setItem('wallet', 'unregistered');
            });
          }
        });
      },
      () => {
        this.isNotHidden = true;
      }
    );

  }

  close() {
    this.onChange.emit();
  }

}
