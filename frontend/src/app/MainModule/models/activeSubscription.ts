import {SubscriptionModel} from "./subscriptionModel";

export class ActiveSubscription {

  id: string;
  lastEditDate: number;
  customerId: number;
  subscription: SubscriptionModel;
  quantity: number;


  constructor(subscription: SubscriptionModel, quantity: number) {
    this.subscription = subscription;
    this.quantity = quantity;
  }

}
