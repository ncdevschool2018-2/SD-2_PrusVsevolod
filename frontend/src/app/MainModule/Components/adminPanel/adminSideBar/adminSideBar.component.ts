import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-adminSideBar',
  templateUrl: './adminSideBar.component.html',
  styleUrls: ['./adminSideBar.component.css']
})
export class AdminSideBarComponent {

  @Input() isCollapsed: boolean = false;

  constructor() {
  }
}
