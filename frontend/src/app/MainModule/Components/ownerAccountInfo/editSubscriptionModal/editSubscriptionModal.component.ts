import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SubscriptionModel} from "../../../models/subscriptionModel";
import {SubscriptionService} from "../../../../services/subscription.service";
import {Owner} from "../../../models/owner";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'modal-editSubscriptionMenu',
  templateUrl: './editSubscriptionModal.component.html',
  styleUrls: ['./editSubscriptionModal.component.css']
})
export class EditSubscriptionModalComponent implements OnInit{

  subscriptionForm: FormGroup;

  @Input() editableSubscription: SubscriptionModel = new SubscriptionModel();
  @Output() onChanged = new EventEmitter();

  // private subOwner: Subscription[] = [];

  constructor(private subscriptionsService: SubscriptionService, private formBuilder: FormBuilder) {
  }

  submit(): void {
    if (this.editableSubscription.owner == null) {
      this.editableSubscription.owner = new Owner();
      this.editableSubscription.owner.id = localStorage.getItem('ownerId');
    }
    this.subscriptionsService.saveSubscription(this.editableSubscription).subscribe(() => {
      this.onChanged.emit();
    });
  }

  close(){
    this.onChanged.emit();
  }

  ngOnInit(): void {
    this.subscriptionForm = this.formBuilder.group({
      name: ['', Validators.required],
      price: ['', Validators.required],
      imageUrl: ['', Validators.required],
      description: ['', Validators.required]
    });
  }
}
