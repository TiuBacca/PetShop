import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, map, catchError } from 'rxjs';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, public router: Router) { }

  logarSistema(login: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/acesso/login`,login).pipe(
      map((response) => response)
    );
  }
 
}