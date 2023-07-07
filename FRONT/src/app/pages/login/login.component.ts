import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

declare var window: any;

@Component({
  selector: 'app-validacao',
  templateUrl: './login.component.html',
  styleUrls: ['/login.component.css']
})
export class LoginComponent implements OnInit {

  data = {
    email: '',
    senha: ''
  }

  constructor( public router: Router, private loginService: LoginService) { }

  ngOnInit(): void {

  }

  recuperarSenha() {
    this.router.navigate(['/recuperar-senha']);
  }

  atualizarSessionStorage(data : any){
    sessionStorage.setItem('token', data.token);
    sessionStorage.setItem('usuario', data.email);
  }

  direcionaHome(){
    this.router.navigate(['/home']);

  }


  acessarSistema(login: any){
    this.loginService.logarSistema(login).subscribe(
      (response) => {
        if (response) {
          this.atualizarSessionStorage(response);
          this.direcionaHome();
        }
      }
    );

  }

}
