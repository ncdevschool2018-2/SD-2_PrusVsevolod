import {User} from "./user";
import {Ba} from "./ba";

export class Owner {
  id: string;
  name: string;
  user: User = new User();
  ba: Ba;

  constructor(){
    if (localStorage.getItem('wallet')){
      this.ba = new Ba();
    }
  }

  static cloneOwner(owner: Owner): Owner{
    let cloneOwner: Owner = new Owner();
    cloneOwner.id = owner.id;
    cloneOwner.name = owner.name;
    cloneOwner.user = owner.user;
    cloneOwner.ba = owner.ba;
    cloneOwner.user = User.cloneUser(owner.user);
    return cloneOwner;
  }
}
