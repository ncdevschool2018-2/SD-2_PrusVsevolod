import {Component, EventEmitter, OnInit, Output, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {SubtractionService} from "../../../../services/subtraction.service";

@Component({
  selector: 'app-adminNavBar',
  templateUrl: './adminNavBar.component.html',
  styleUrls: ['./adminNavBar.component.css']
})
export class AdminNavBarComponent implements OnInit {

  @Output() isCollapsed: boolean = false;
  @Output() onCollapse = new EventEmitter<boolean>();
  threshold: number;
  inputValue: number;
  private bsModalRef: BsModalRef;

  constructor(private modalService: BsModalService, private subtractionService: SubtractionService) {
  }

  change(): void {
    this.isCollapsed = !this.isCollapsed;
    this.onCollapse.emit(this.isCollapsed);
  }

  openEditModal(template: TemplateRef<any>): void {
    this.bsModalRef = this.modalService.show(template);
  }

  confirm(): void {
    this.subtractionService.editThreshold(this.inputValue).subscribe(() => {
      this.threshold = this.inputValue;
      this.inputValue = null;
      this.close();
    })
  }

  close(): void {
    this.bsModalRef.hide();
  }

  loadThreshold(): void {
    this.subtractionService.getThreshold().subscribe(value => {
      this.threshold = value;
    })
  }

  ngOnInit(): void {
    this.loadThreshold()
  }

}
