import {SubscriptionModel} from "./subscriptionModel";

export class Sb {

  id: string;
  customerId: string;
  subscription: SubscriptionModel = new SubscriptionModel();
  quantity: number;

  constructor(customerId: string, subscription: SubscriptionModel, quantity: number){
    this.customerId = customerId;
    this.subscription = subscription;
    this.quantity = quantity;
  }
}


