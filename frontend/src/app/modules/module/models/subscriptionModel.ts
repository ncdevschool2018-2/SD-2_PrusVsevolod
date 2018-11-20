import {Owner} from "./owner";

export class SubscriptionModel {
  id: string;
  name: string;
  image_url: string;
  description: string;
  price: number;
  owner: Owner;

  static cloneSubscription(subscription: SubscriptionModel): SubscriptionModel {
    let clonedSubscription: SubscriptionModel = new SubscriptionModel();
    clonedSubscription.id = subscription.id;
    clonedSubscription.name = subscription.name;
    clonedSubscription.image_url = subscription.image_url;
    clonedSubscription.description = subscription.description;
    clonedSubscription.price = subscription.price;
    clonedSubscription.owner = Owner.cloneOwner(subscription.owner);
    return clonedSubscription;
  }
}
