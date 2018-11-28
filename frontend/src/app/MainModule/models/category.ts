export class Category{

  id: string;
  name: string;

  static cloneCategory(category: Category): Category{
    let cloneCategory: Category = new Category();
    cloneCategory.id = category.id;
    cloneCategory.name = category.name;
    return cloneCategory;
  }
}
