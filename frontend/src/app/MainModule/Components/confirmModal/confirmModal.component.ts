import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'modal-confirm',
  templateUrl: './confirmModal.component.html',
  styleUrls: ['./confirmModal.component.css']
})
export class ConfirmModalComponent {

  @Output() onConfirm = new EventEmitter();
  @Output() onDecline = new EventEmitter();

  constructor() {
  }

  confirm() {
    this.onConfirm.emit();
  }

  decline() {
    this.onDecline.emit();
  }

}
