import {Sort} from "./sort";

export class Pageable{

  sort: Sort;
  offset: number;
  pageSize: number;
  pageNumber: number;
  paged: boolean;
  unpaged: boolean;
}
