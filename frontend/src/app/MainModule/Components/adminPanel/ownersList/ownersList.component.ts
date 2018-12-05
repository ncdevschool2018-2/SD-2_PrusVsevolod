import {Component, OnInit, TemplateRef} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalRef, BsModalService, PageChangedEvent} from "ngx-bootstrap";
import {Owner} from "../../../models/owner";
import {OwnerService} from "../../../../services/owner.service";

@Component({
  selector: 'app-ownersList',
  templateUrl: './ownersList.component.html',
  styleUrls: ['./ownersList.component.css']
})
export class OwnersListComponent implements OnInit {

  bsModalRef: BsModalRef;
  isCollapsed: boolean = false;

  owners: Owner[] = [];
  editableOwner: Owner;
  size: number = 10;
  totalElements: number = 0;
  page: number = 0;

  // private subUsers: Subscription[] = [];

  constructor(private loadingService: Ng4LoadingSpinnerService, private ownersService: OwnerService, private modalService: BsModalService) {
  }

  // Calls on component init
  ngOnInit() {
    this.loadOwners(this.page, this.size);
  }

  private loadOwners(page: number, size: number): void {
    this.loadingService.show();
    this.ownersService.getOwners(page, size).subscribe(source => {
      this.owners = source.content;
      this.totalElements = source.totalElements;
      this.loadingService.hide();
    });
  }

  public deleteOwner(id: string): void {
    this.ownersService.deleteOwner(id).subscribe(() => {
      this.loadOwners(this.page, this.size);
      // this.loadUsers(0, this.size);
    });
  }

  openOwnerEditModal(template: TemplateRef<any>, owner: Owner) {
    this.editableOwner = Owner.cloneOwner(owner);
    this.bsModalRef = this.modalService.show(template, {class: 'modal-lg'});
  }

  closeEditOwnerModal(): void {
    this.loadOwners(this.page, this.size);
    this.bsModalRef.hide();
  }

  confirmDeleteOwner(id: string) {
    this.deleteOwner(id);
    this.bsModalRef.hide();
  }

  decline() {
    this.bsModalRef.hide();
  }

  openConfirmModal(template: TemplateRef<any>) {
    this.bsModalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  pageChanged(event: PageChangedEvent): void {
    this.page = event.page -1;
    this.loadOwners(this.page, this.size);
  }

  collapse(isCollapsed: boolean):void{
    this.isCollapsed = isCollapsed;
  }
}
