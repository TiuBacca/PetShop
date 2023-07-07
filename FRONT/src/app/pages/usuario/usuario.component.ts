import { Component, OnInit, AfterViewInit } from '@angular/core';
import { UsuarioService } from './usuario.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

declare var window: any;

@Component({
  selector: 'app-home',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  camposPreenchidos: boolean = false;

  tempoNotificacao = 2000;
  usuarioLogado = sessionStorage.getItem('usuario');

  modalNovoUsuario: any;
  modalVisualizarPersonagens: any;


  paginaInicial = 1;
  paginaInicialTabelaPericias = 1;

  usuario = {
    id: 0,
    nome: '',
    email: '',
    senha: ''
  }

  usuarioEdicao = {
    id: 0,
    nome: '',
    email: '',
    senha: ''
  }


  listaUsuarios: any;
  listaPersonagens: any[] = [];

  filtroNome: any;

  constructor(
    private usuarioService: UsuarioService,
    public router: Router) { }



  ngOnInit(): void {
    this.inicilizaModais();
  }


  inicilizaModais() {

    this.modalNovoUsuario = new window.bootstrap.Modal(
      document.getElementById('modalNovoUsuario')
    );

    this.modalVisualizarPersonagens = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarPersonagens')
    );


  }

  verificarCamposPreenchidos() {
    this.camposPreenchidos = !!this.usuarioEdicao.nome && !!this.usuarioEdicao.email && !!this.usuarioEdicao.senha;
  }

  executarPesquisaByFiltro() {

    const data = {
      nome: this.filtroNome
    }

    this.usuarioService.executarPesquisaByFiltro(data).subscribe((response) => {
      if (response) {
        this.listaUsuarios = response;
      } else {
        this.listaUsuarios = [];
      }

    });
  }

  limparFiltros() {
    this.filtroNome = null;
    this.listaUsuarios = [];
  }

  removerUsuario(data: any) {
    Swal.fire({
      title: `Você realmente deseja excluir o usuário?`,
      showDenyButton: false,
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Excluir',
      confirmButtonColor: '#ff1100',
    }).then((result) => {
      if (result.isConfirmed) {
        this.usuarioService.removerUsuario(data).subscribe((response) => {
          if (response) {
            this.notificacaoSucesso('Usuario excluido com sucesso!');
            this.executarPesquisaByFiltro();
          }
        });
      }
    })

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

  abrirModalNovoUsuario() {
    this.alimentaModalUsuario();
    this.modalNovoUsuario.show();
  }


  alimentaModalUsuario(){
    this.verificarCamposPreenchidos();
    this.usuarioEdicao = {
      id: this.usuario.id,
      nome: this.usuario.nome,
      email: this.usuario.email,
      senha: this.usuario.senha
    }
  }

  fecharModalNovoUsuario() {
    this.limparModalNovoUsuario();
    this.modalNovoUsuario.hide();
  }


  limparModalNovoUsuario() {
    this.usuario = {
      id: 0,
      nome: '',
      email: '',
      senha: ''
    }
  }

  salvarNovoUsuario() {

    this.usuarioEdicao.id = this.usuario.id; 

    this.usuarioService.salvarUsuario(this.usuarioEdicao).subscribe((response) => {
      if (response) {
        this.listaPersonagens = response;
        this.fecharModalNovoUsuario();
        this.notificacaoSucesso("Usuário salvo com sucesso.");
        setTimeout(() => {
          this.executarPesquisaByFiltro();
        }, 50);
      } else {
        console.log(response)
        this.mensagemError("Erro ao salvar usuário.")
      }
    });

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

  editarUsuario(usuario: any) {
    this.usuario = usuario;
    this.abrirModalNovoUsuario();

  }


  abrirModalVisualizarPersonagens(usuario: any) {
    var data = {
      usuarios: [{ id: usuario.id }]
    }

    this.usuarioService.buscarListaPersonagens(data).subscribe((response) => {
      if (response) {
        this.listaPersonagens = response;
        this.modalVisualizarPersonagens.show();
      } else {
        this.listaPersonagens = [];
      }
    });
  }

  fecharModalVisualizarPersonagens() {
    this.modalVisualizarPersonagens.hide();
  }




}
