import {Component} from '@angular/core';
import {Owner} from "../../models/owner";
import {OwnerService} from "../../../services/owner.service";
import {Subscription} from "rxjs";
import {BsModalRef} from "ngx-bootstrap";

@Component({
  selector: 'modal-editOwnerMenu',
  templateUrl: './editOwnerModal.component.html',
  styleUrls: ['./editOwnerModal.component.css']
})
export class EditOwnerModalComponent{

  public editableOwner: Owner = new Owner();
  private subOwner: Subscription[] = [];

  constructor(private ownersService: OwnerService, public bsModalRef: BsModalRef){}

  saveOwner(){
    // console.log(this.editableOwner.name);
    this.subOwner.push(this.ownersService.saveEditedOwner(this.editableOwner).subscribe(() => {
    }));
  }

}
