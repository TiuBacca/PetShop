import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, catchError } from 'rxjs';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})

export class HomeService {

  baseUrl = environment.baseUrl;

  autorization = {
    "Authorization": 'Bearer' + sessionStorage.getItem('tokenSistema')
  }

  constructor(private http: HttpClient) { }

  buscaListaPersonagensDoUsuarioLogado(usuario: string): Observable<any> {

    const data = {
      email: usuario
    };

    return this.http.post<any>(`${this.baseUrl}/personagem/buscaLista/byEmail`, data).pipe(
      map((response) => {
        if (response.status === 204) {
          console.log('Retorno 204 recebido');
        }
        return response;
      }),
      catchError(async (error) => this.erroHandler(error))
    );
  }


  direcionarTelaPersonagem(personagem: any) {

    Swal.fire({
      title: `Você deseja salvar seu 1° personagem?`,
      showDenyButton: false,
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Excluir',
      confirmButtonColor: '#ff1100',
    }).then((result) => {
      if (result.isConfirmed) {

      }
    })
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
    if (error.status == 500) {
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
        title: error.error.message
      })
    }
  }
}
