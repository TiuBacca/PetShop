import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  constructor(public router: Router) { }

  idPerfilPermissao: number = 0;

  ngOnInit(): void {
    let string = sessionStorage.getItem('idPerfilPermissao');
    this.idPerfilPermissao = Number(string);
    
  }

  logout() {
    sessionStorage.clear();
    this.router.navigate(['/'])
  }

}
