import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SubscriptionModel} from "../../../models/subscriptionModel";
import {SubscriptionService} from "../../../../services/subscription.service";
import {Owner} from "../../../models/owner";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Category} from "../../../models/category";
import {CategoryService} from "../../../../services/category.service";

@Component({
  selector: 'modal-editSubscriptionMenu',
  templateUrl: './editSubscriptionModal.component.html',
  styleUrls: ['./editSubscriptionModal.component.css']
})
export class EditSubscriptionModalComponent implements OnInit {

  subscriptionForm: FormGroup;

  @Input() editableSubscription: SubscriptionModel = new SubscriptionModel();
  @Output() onChanged = new EventEmitter();
  categories: Category[] = [];

  constructor(private subscriptionsService: SubscriptionService, private formBuilder: FormBuilder, private categoryService: CategoryService) {
  }

  submit(): void {
    if (this.editableSubscription.owner == null) {
      this.editableSubscription.owner = new Owner();
      this.editableSubscription.owner.id = localStorage.getItem('ownerId');
    }

    if (this.editableSubscription.category == null) {
      this.editableSubscription.category = new Category();
    }

    this.categories.forEach(category =>{
      if(category.name == this.subscriptionForm.controls['category'].value) {
        this.editableSubscription.category.id = category.id;
      }
    });

    this.subscriptionsService.saveSubscription(this.editableSubscription).subscribe(() => {
      this.onChanged.emit();
    });
  }

  close() {
    this.onChanged.emit();
  }

  ngOnInit(): void {
    this.loadCategories();

    this.subscriptionForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(20), Validators.pattern("^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$")]],
      price: ['', [Validators.required, Validators.min(1), Validators.max(999)]],
      imageUrl: ['', Validators.required],
      description: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(100)]],
      category: []
    });


    // this.subscriptionForm = new FormGroup({
    //   name: new FormControl('', Validators.required),
    //   price: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(5)]),
    //   imageUrl: new FormControl('', Validators.required),
    //   description: new FormControl('', Validators.required),
    //   category: new FormControl()
    // });

    if (this.editableSubscription.category != null) {
      this.subscriptionForm.controls['category'].setValue(this.editableSubscription.category.name);
    } else {
      this.subscriptionForm.controls['category'].setValue('Other');
    }
  }

  loadCategories(): void {
    this.categoryService.getCategories().subscribe(source => {
      this.categories = source as Category[];
    })
  }

  equalSubscriptionCategory(category: Category): boolean {
    if (this.editableSubscription.category == null && category.name == 'Other') {
      return true;
    } else if (this.editableSubscription.category != null && this.editableSubscription.category.id === category.id) {
      return true;
    }
    return false;
  }


}
