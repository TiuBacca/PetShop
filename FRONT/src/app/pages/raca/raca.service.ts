import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, catchError } from 'rxjs';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})

export class RacaService {

  baseUrl = environment.baseUrl;

  autorization = {
    "Authorization": 'Bearer' + sessionStorage.getItem('tokenSistema')
  }

  constructor(private http: HttpClient) { }

  executarPesquisaByFiltro(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/raca/buscaLista/byFiltro`, data).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );

  }

  listarHabilidades(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/raca/buscaLista/habilidades`, data ).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  salvarRaca(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/raca/salvar`, data ).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  removerRaca(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/raca/excluir`, data ).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }
  

  erroHandler(error: any): any {
    console.log(error)
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
