import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class GuardaRotaService implements CanActivate  {

  constructor(private router: Router) { }

  canActivate(router: ActivatedRouteSnapshot): Observable<boolean> | boolean {

      // const token = sessionStorage.getItem('token');
      // return token ? true : (this.router.navigate(['/']), false);
    return true;       
  }
}