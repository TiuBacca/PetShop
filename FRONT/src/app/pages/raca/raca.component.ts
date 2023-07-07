import { Component, OnInit, AfterViewInit } from '@angular/core';
import { RacaService } from './raca.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

declare var window: any;

@Component({
  selector: 'app-home',
  templateUrl: './raca.component.html',
  styleUrls: ['./raca.component.css']
})
export class RacaComponent implements OnInit {

  camposPreenchidos: boolean = false;

  tempoNotificacao = 2000;

  modalNovaRaca: any;
  modalHabilidades: any;


  paginaInicial = 1;
  paginaInicialHabilidades = 1;

  raca = {
    id: 0,
    nome: '',
    descricao: ''
  }

  racaEdicao = {
    id: 0,
    nome: '',
    descricao: ''
  }


  listaRaca: any;
  listaHabilidades: any[] = [];

  filtroNome: any;

  constructor(
    private racaService: RacaService,
    public router: Router) { }



  ngOnInit(): void {
    this.inicilizaModais();
  }


  inicilizaModais() {

    this.modalNovaRaca = new window.bootstrap.Modal(
      document.getElementById('modalNovaRaca')
    );

    this.modalHabilidades = new window.bootstrap.Modal(
      document.getElementById('modalHabilidades')
    );


  }

  verificarCamposPreenchidos() {
    this.camposPreenchidos = !!this.racaEdicao.nome && !!this.racaEdicao.descricao;
  }

  executarPesquisaByFiltro() {

    const data = {
      nome: this.filtroNome
    }

    this.racaService.executarPesquisaByFiltro(data).subscribe((response) => {
      if (response) {
        this.listaRaca = response;
      } else {
        this.listaRaca = [];
      }
    });

    this.paginaInicial = 1;
  }

  limparFiltros() {
    this.filtroNome = null;
    this.listaRaca = [];
  }

  removerRaca(data: any) {
    Swal.fire({
      title: `Você realmente deseja excluir a raça?`,
      showDenyButton: false,
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Excluir',
      confirmButtonColor: '#ff1100',
    }).then((result) => {
      if (result.isConfirmed) {
        this.racaService.removerRaca(data).subscribe((response) => {
          if (response) {
            this.notificacaoSucesso('Classe excluida com sucesso!');
            this.executarPesquisaByFiltro();
          }
        });
      }
    })

  }



  abrirModalNovaRaca() {
    this.alimentaModalRaca();
    this.modalNovaRaca.show();
  }


  alimentaModalRaca(){
    this.verificarCamposPreenchidos();
    this.racaEdicao = {
      id: this.raca.id,
      nome: this.raca.nome,
      descricao: this.raca.descricao
    }
  }

  fecharModalNovaRaca() {
    this.limparModalNovaRaca();
    this.modalNovaRaca.hide();
  }


  limparModalNovaRaca() {
    this.raca = {
      id: 0,
      nome: '',
      descricao: ''
    }
  }

  salvarNovaRaca() {

    console.log(this.racaEdicao)
    this.racaService.salvarRaca(this.racaEdicao).subscribe((response) => {
      if (response) {
        this.raca = response;
        this.fecharModalNovaRaca();
        this.notificacaoSucesso("Raça salva com sucesso.");
        setTimeout(() => {
          this.executarPesquisaByFiltro();
        }, 50);
      } else {
        console.log(response)
        this.mensagemError("Erro ao salvar raça.")
      }
    });

  }


  editarRaca(raca: any) {
    this.raca = raca;
    this.abrirModalNovaRaca();

  }


  listarHabilidades(raca: any) {

    this.racaService.listarHabilidades(raca).subscribe((response) => {
      if (response) {
        this.listaHabilidades = response;
        this.modalHabilidades.show();
      } else {
        this.listaHabilidades = [];
      }
    });
  }

  fecharModalHabilidades() {
    this.modalHabilidades.hide();
  }


  notificacaoSucesso(mensagem: any) {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: this.tempoNotificacao,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    })
    Toast.fire({
      icon: 'success',
      title: mensagem
    })
  }

  mensagemError(mensagem: any) {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: this.tempoNotificacao,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    })
    Toast.fire({
      icon: 'error',
      title: mensagem
    })
  }

}
