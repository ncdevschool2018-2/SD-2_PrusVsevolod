import {Owner} from "./owner";
import {Category} from "./category";

export class SubscriptionModel {
  id: string;
  name: string;
  imageUrl: string;
  description: string;
  price: number;
  owner: Owner;
  category: Category;

  static cloneSubscription(subscription: SubscriptionModel): SubscriptionModel {
    let clonedSubscription: SubscriptionModel = new SubscriptionModel();
    clonedSubscription.id = subscription.id;
    clonedSubscription.name = subscription.name;
    clonedSubscription.imageUrl = subscription.imageUrl;
    clonedSubscription.description = subscription.description;
    clonedSubscription.price = subscription.price;
    clonedSubscription.owner = Owner.cloneOwner(subscription.owner);
    clonedSubscription.category = Category.cloneCategory(subscription.category);
    return clonedSubscription;
  }
}
