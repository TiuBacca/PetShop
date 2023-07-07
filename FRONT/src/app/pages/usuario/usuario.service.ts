import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, catchError } from 'rxjs';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})

export class UsuarioService {

  baseUrl = environment.baseUrl;

  autorization = {
    "Authorization": 'Bearer' + sessionStorage.getItem('tokenSistema')
  }

  constructor(private http: HttpClient) { }

  executarPesquisaByFiltro(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/usuario/buscaLista/byFiltro`, data).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );

  }

  buscarListaPersonagens(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/personagem/buscaLista/byFiltro`, data ).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  salvarUsuario(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/usuario/salvar`, data ).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  removerUsuario(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/usuario/excluir`, data ).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }
  

  erroHandler(error: any): any {
    if (error.status == 504) {
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      })
      
      Toast.fire({
        icon: 'error',
        title: 'Falha ao se comunicar com o servidor!'
      })
    }
    if(error.status == 500) {
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      })
      
      Toast.fire({
        icon: 'error',
        title: error.error.mensagem
      })
    }
  }
}
