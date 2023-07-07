import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ClasseService } from './classe.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

declare var window: any;

@Component({
  selector: 'app-home',
  templateUrl: './classe.component.html',
  styleUrls: ['./classe.component.css']
})
export class ClasseComponent implements OnInit {

  camposPreenchidos: boolean = false;

  tempoNotificacao = 2000;

  modalNovaClasse: any;
  modalHabilidades: any;


  paginaInicial = 1;
  paginaInicialHabilidades = 1;

  classe = {
    id: 0,
    nome: '',
    descricao: '',
    pontosBaseAtaque: 0,
    pontosBaseVida: 0
  }

  classeEdicao = {
    id: 0,
    nome: '',
    descricao: '',
    pontosBaseAtaque: 0,
    pontosBaseVida: 0
  }


  listaClasse: any;
  listaHabilidades: any[] = [];

  filtroNome: any;

  constructor(
    private classeService: ClasseService,
    public router: Router) { }



  ngOnInit(): void {
    this.inicilizaModais();
  }


  inicilizaModais() {

    this.modalNovaClasse = new window.bootstrap.Modal(
      document.getElementById('modalNovaClasse')
    );

    this.modalHabilidades = new window.bootstrap.Modal(
      document.getElementById('modalHabilidades')
    );


  }

  verificarCamposPreenchidos() {
    this.camposPreenchidos = !!this.classeEdicao.nome && !!this.classeEdicao.descricao;
  }

  executarPesquisaByFiltro() {

    const data = {
      nome: this.filtroNome
    }

    this.classeService.executarPesquisaByFiltro(data).subscribe((response) => {
      if (response) {
        this.listaClasse = response;
      } else {
        this.listaClasse = [];
      }

    });
  }

  limparFiltros() {
    this.filtroNome = null;
    this.listaClasse = [];
  }

  removerClasse(data: any) {
    Swal.fire({
      title: `Você realmente deseja excluir a classe?`,
      showDenyButton: false,
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Excluir',
      confirmButtonColor: '#ff1100',
    }).then((result) => {
      if (result.isConfirmed) {
        this.classeService.removerClasse(data).subscribe((response) => {
          if (response) {
            this.notificacaoSucesso('Classe excluida com sucesso!');
            this.executarPesquisaByFiltro();
          }
        });
      }
    })

  }



  abrirModalNovaClasse() {
    this.alimentaModalClasse();
    this.modalNovaClasse.show();
  }


  alimentaModalClasse(){
    this.verificarCamposPreenchidos();
    this.classeEdicao = {
      id: this.classe.id,
      nome: this.classe.nome,
      descricao: this.classe.descricao,
      pontosBaseAtaque: this.classe.pontosBaseAtaque,
      pontosBaseVida: this.classe.pontosBaseVida
    }
  }

  fecharModalNovaClasse() {
    this.limparModalNovaClasse();
    this.modalNovaClasse.hide();
  }


  limparModalNovaClasse() {
    this.classe = {
      id: 0,
      nome: '',
      descricao: '',   
      pontosBaseAtaque: 0,
      pontosBaseVida: 0
    }
  }

  salvarNovaClasse() {

    console.log(this.classeEdicao)
    this.classeService.salvarClasse(this.classeEdicao).subscribe((response) => {
      if (response) {
        this.classe = response;
        this.fecharModalNovaClasse();
        this.notificacaoSucesso("Classe salva com sucesso.");
        setTimeout(() => {
          this.executarPesquisaByFiltro();
        }, 50);
      } else {
        console.log(response)
        this.mensagemError("Erro ao salvar classe.")
      }
    });

  }


  editarClasse(classe: any) {
    this.classe = classe;
    this.abrirModalNovaClasse();

  }


  listarHabilidades(classe: any) {

    this.classeService.listarHabilidades(classe).subscribe((response) => {
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
