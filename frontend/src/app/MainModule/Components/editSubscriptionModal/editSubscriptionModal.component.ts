import {Component, OnInit} from '@angular/core';
import {BsModalRef} from "ngx-bootstrap";
import {SubscriptionModel} from "../../models/subscriptionModel";
import {SubscriptionService} from "../../../services/subscription.service";
import {Owner} from "../../models/owner";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'modal-editSubscriptionMenu',
  templateUrl: './editSubscriptionModal.component.html',
  styleUrls: ['./editSubscriptionModal.component.css']
})
export class EditSubscriptionModalComponent implements OnInit{

  subscriptionForm: FormGroup;

  public editableSubscription: SubscriptionModel = new SubscriptionModel();

  // private subOwner: Subscription[] = [];

  constructor(private subscriptionsService: SubscriptionService, public bsModalRef: BsModalRef, private formBuilder: FormBuilder) {
  }

  Submit(): void {
    if (this.editableSubscription.owner == null) {
      this.editableSubscription.owner = new Owner();
      this.editableSubscription.owner.id = localStorage.getItem('ownerId');
    }
    this.subscriptionsService.saveSubscription(this.editableSubscription).subscribe(() => {
    });
    this.bsModalRef.hide()
  }

  ngOnInit(): void {
    this.subscriptionForm = this.formBuilder.group({
      name: ['', Validators.required],
      price: ['', Validators.required],
      image_url: ['', Validators.required],
      description: ['', Validators.required]
    });
  }
}
