import {Sort} from "./sort";
import {Pageable} from "./pageable";

export class Content<T> {

  content: T[];
  pageable: Pageable;
  last: boolean;
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
  sort: Sort;
  numberOfElements: number;
  first: boolean;
}
