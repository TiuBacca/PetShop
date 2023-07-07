import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, catchError } from 'rxjs';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})

export class PersonagemService {

  baseUrl = environment.baseUrl;

  autorization = {
    "Authorization": 'Bearer' + sessionStorage.getItem('tokenSistema')
  }

  constructor(private http: HttpClient) { }

  executarPesquisaByFiltro(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/personagem/buscaLista/byFiltro`, data).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );

  }

  buscaListaPersonagensDoUsuarioLogado(usuario: string): Observable<any> {

    const data = {
      email: usuario
    };

    return this.http.post<any>(`${this.baseUrl}/personagem/buscaLista/byEmail`, data).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  buscarListaUsuarios(): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/usuario/buscaLista/byFiltro`, {} ).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  buscarListaSexos(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/personagem/buscaLista/sexo`).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  buscarListaClasse(): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/classe/buscaLista/byFiltro`, {}).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  buscarListaRaca(): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/raca/buscaLista/byFiltro`, {}).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  buscarListaTendencia(): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/tendencia/buscaLista/byFiltro`, {}).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  buscarListaArmas(idPersonagem: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/arma/buscaLista`).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  buscarListaArmaduras(idPersonagem: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/armadura/buscaLista`).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  buscarListaEscudos(idPersonagem: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/escudo/buscaLista`).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  buscarListaInfoCorpoCorpo(idPersonagem: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/personagem/infoCorpoACorpo`, {}).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  buscarListaInfoDistancia(idPersonagem: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/personagem/infoDistancia`, {}).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }


  buscarListaHabilidadeClasse(classe: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/classe/buscaLista/habilidades`, classe).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }


  buscarListaHabilidadeRaca(raca: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/raca/buscaLista/habilidades`, raca).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  buscarListaPericias(idPersonagem: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/personagem/buscaLista/pericias`, {}).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }

  salvarPersonagem(personagem: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/personagem/salvar`, personagem).pipe(
      map((response) => response),
      catchError(async (error) => this.erroHandler(error))
    );
  }


  removerPersonagem(personagem: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/personagem/excluir`, personagem).pipe(
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
        title: error.error.message
      })
    }
  }
}
