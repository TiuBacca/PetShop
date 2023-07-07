import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: any

  constructor() { }

  ngOnInit(): void {
    document.body.classList.toggle('toggle-sidebar');
    this.user = sessionStorage.getItem('usuario');
  }

  toggleSidebar() {
    document.body.classList.toggle('toggle-sidebar');
  }

}
