import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Owner} from "../../../models/owner";
import {OwnerService} from "../../../../services/owner.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'modal-editOwnerMenu',
  templateUrl: './editOwnerModal.component.html',
  styleUrls: ['./editOwnerModal.component.css']
})
export class EditOwnerModalComponent {

  @Input() editableOwner: Owner = new Owner();
  @Output() onChanged = new EventEmitter();

  constructor(private ownersService: OwnerService) {
  }

  saveOwner() {
    this.ownersService.saveEditedOwner(this.editableOwner).subscribe(() => {
      this.onChanged.emit();
    });
  }

}
