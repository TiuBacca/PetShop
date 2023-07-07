import { Component, OnInit, AfterViewInit } from '@angular/core';
import { TendenciaService } from './tendencia.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

declare var window: any;

@Component({
  selector: 'app-home',
  templateUrl: './tendencia.component.html',
  styleUrls: ['./tendencia.component.css']
})
export class TendenciaComponent implements OnInit {

  camposPreenchidos: boolean = false;

  tempoNotificacao = 2000;

  modalNovaTendencia: any;

  paginaInicial = 1;

  tendencia = {
    id: 0,
    descricao: ''
  }

  tendenciaEdicao = {
    id: 0,
    descricao: ''
  }


  listaTendencia: any;

  filtroNome: any;

  constructor(
    private tendenciaService: TendenciaService,
    public router: Router) { }



  ngOnInit(): void {
    this.inicilizaModais();
  }


  inicilizaModais() {

    this.modalNovaTendencia = new window.bootstrap.Modal(
      document.getElementById('modalNovaTendencia')
    );

  }

  verificarCamposPreenchidos() {
    this.camposPreenchidos = !!this.tendenciaEdicao.descricao;
  }

  executarPesquisaByFiltro() {

    const data = {
      descricao: this.filtroNome
    }

    this.tendenciaService.executarPesquisaByFiltro(data).subscribe((response) => {
      if (response) {
        this.listaTendencia = response;
      } else {
        this.listaTendencia = [];
      }
    });

    this.paginaInicial = 1;
  }

  limparFiltros() {
    this.filtroNome = null;
    this.listaTendencia = [];
  }

  removerTendencia(data: any) {
    Swal.fire({
      title: `Você realmente deseja excluir a tendência?`,
      showDenyButton: false,
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Excluir',
      confirmButtonColor: '#ff1100',
    }).then((result) => {
      if (result.isConfirmed) {
        this.tendenciaService.removerTendencia(data).subscribe((response) => {
          if (response) {
            this.notificacaoSucesso('Tendência excluida com sucesso!');
            this.executarPesquisaByFiltro();
          }
        });
      }
    })

  }



  abrirModalNovaTendencia() {
    this.alimentaModalTendencia();
    this.modalNovaTendencia.show();
  }


  alimentaModalTendencia(){
    this.verificarCamposPreenchidos();
    this.tendenciaEdicao = {
      id: this.tendencia.id,
      descricao: this.tendencia.descricao
    }
  }

  fecharModalNovaTendencia() {
    this.limparModalNovaTendencia();
    this.modalNovaTendencia.hide();
  }


  limparModalNovaTendencia() {
    this.tendencia = {
      id: 0,
      descricao: ''
    }
  }

  salvarNovaTendencia() {

    console.log(this.tendenciaEdicao)
    this.tendenciaService.salvarTendencia(this.tendenciaEdicao).subscribe((response) => {
      if (response) {
        this.tendencia = response;
        this.fecharModalNovaTendencia();
        this.notificacaoSucesso("Tendência salva com sucesso.");
        setTimeout(() => {
          this.executarPesquisaByFiltro();
        }, 50);
      } else {
        console.log(response)
        this.mensagemError("Erro ao tendência raça.")
      }
    });

  }


  editarTendencia(tendencia: any) {
    this.tendencia = tendencia;
    this.abrirModalNovaTendencia();

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
