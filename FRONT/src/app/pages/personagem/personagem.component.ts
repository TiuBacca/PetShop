import { Component, OnInit } from '@angular/core';
import { PersonagemService } from './personagem.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

declare var window: any;

@Component({
  selector: 'app-home',
  templateUrl: './personagem.component.html',
  styleUrls: ['./personagem.component.css']
})
export class PersonagemComponent implements OnInit {

  tempoNotificacao = 2000;

  modalVisualizarPersonagem: any;
  modalUparPersonagem: any;
  modalArmas: any;
  modalArmaduras: any;
  modalEscudos: any;
  modalCorpoCorpo: any;
  modalVisualizarDistancia: any;
  modalVisualizarHabilidades: any;
  modalVisualizarPericia: any;

  modalNovoPersonagem: any;


  paginaInicial = 1;
  paginaInicialTabelaPericias = 1;
  paginaInicialTabelaArmas = 1;
  paginaInicialTabelaArmaduras = 1;
  paginaInicialTabelaEscudos = 1;
  paginaInicialTabelaHabilidadeRaca = 1;
  paginaInicialTabelaHabilidadeClasse = 1;


  infoCorpoACorpo = {
    bonusBaseAtaque: 0,
    modificadorHabilidade: 0,
    modificadorTamanho: 0,
    outros: 0
  }

  data = {
    id: 0,
    usuarios: {},
    sexos: {},
    classes: {},
    racas: {},
    tendencias: {}
  }

  personagem = {
    id: 0,
    nome: "",
    raca: {
      id: 0,
      nome: ""
    },
    classe: {
      id: 0,
      nome: "",
      pontosBaseVida: 0,
      pontosBaseAtaque: 0
    },
    tendencia: {
      id: 0,
      nome: ''
    },
    fortitude: 0,
    reflexo: 0,
    vontade: 0,
    sexo: "",
    forca: 0,
    destreza: 0,
    constituicao: 0,
    inteligencia: 0,
    sabedoria: 0,
    carisma: 0,
    classeArmadura: 0,
    vidaTotal: 0,
    vidaAtual: 0
  }

  personagemEditar = {
    id: 0,
    nome: "",
    raca: {
      id: 0,
      nome: ""
    },
    classe: {
      id: 0,
      nome: "",
      pontosBaseVida: 0,
      pontosBaseAtaque: 0
    },
    tendencia: {
      id: 0,
      nome: ''
    },
    fortitude: 0,
    reflexo: 0,
    vontade: 0,
    sexo: "",
    forca: 0,
    destreza: 0,
    constituicao: 0,
    inteligencia: 0,
    sabedoria: 0,
    carisma: 0,
    classeArmadura: 0,
    vidaTotal: 0,
    vidaAtual: 0

  }

  listaPersonagens: any;
  listaPericias: any[] = [];
  listaArmas: any[] = [];
  listaArmaduras: any[] = [];
  listaEscudos: any[] = [];
  listaHabilidadesRaca: any[] = [];
  listaHabilidadesClasse: any[] = [];

  listaUsuarios: any;
  selectedItemsUsuarios: any;

  listaSexos: any;
  selectedItemsSexos: any;

  listaClasses: any;
  selectedItemsClasses: any;

  selectedItemClasseModalEditar: any;
  dropdownSettingsClasseModalEditar: any;
  selectedItemClassesModalEditar: any;


  listaRacas: any;
  selectedItemsRacas: any;

  listaTendencias: any;
  dropdownSettingsTendencias: any;
  selectedItemsTendencias: any;

  dropdownSettingsGenerico: any;


  nomeNovoPersonagem: any;

  exibirModalEditarPersonagem: boolean = false;


  constructor(
    private personagemService: PersonagemService,
    public router: Router) { }



  ngOnInit(): void {
    this.inicializaCombos();
    this.inicializaDropDowns();
    this.inicilizaModais();
  }

  teste() {

  }

  inicilizaModais() {

    this.modalVisualizarPersonagem = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarPersonagem')
    );

    this.modalArmas = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarArmas')
    );

    this.modalUparPersonagem = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarPersonagem')
    );

    this.modalArmaduras = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarArmaduras')
    );

    this.modalEscudos = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarEscudos')
    );

    this.modalCorpoCorpo = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarCorpo-Corpo')
    );

    this.modalVisualizarDistancia = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarDistancia')
    );

    this.modalVisualizarHabilidades = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarHabilidades')
    );

    this.modalVisualizarPericia = new window.bootstrap.Modal(
      document.getElementById('modalVisualizarPericia')
    );

    this.modalNovoPersonagem = new window.bootstrap.Modal(
      document.getElementById('modalNovoPersonagemTeste')
    );

  }

  executarPesquisaByFiltro() {

    const data = {
      id: 0,
      usuarios: this.selectedItemsUsuarios,
      sexos: this.selectedItemsSexos,
      classes: this.selectedItemsClasses,
      racas: this.selectedItemsRacas,
      tendencias: this.selectedItemsTendencias
    }

    this.personagemService.executarPesquisaByFiltro(data).subscribe((response) => {
      if (response) {
        this.listaPersonagens = response;
      } else {
        this.listaPersonagens = [];
      }

    });
  }

  baixarFicha(idPersonagem: number) {

  }

  limparFiltros() {
    this.selectedItemsUsuarios = [];
    this.selectedItemsSexos = [];
    this.selectedItemsClasses = [];
    this.selectedItemsRacas = [];
    this.selectedItemsTendencias = [];
    this.listaPersonagens = [];
  }

  selecionarUsuario(usuario: any) {
  }

  selecionarClasse(classe: any) {
  }

  selecionarRaca(raca: any) {
  }

  selecionarSexo(sexo: any) {
  }

  selecionarTendencia(tendencia: any) {
  }

  buscaUsuarios() {
    this.personagemService.buscarListaUsuarios().subscribe((response) => {
      this.listaUsuarios = response;
    });
  }

  buscaSexos() {
    this.personagemService.buscarListaSexos().subscribe((response) => {
      this.listaSexos = response;
    });
  }

  buscaClasses() {
    this.personagemService.buscarListaClasse().subscribe((response) => {
      this.listaClasses = response;
    });
  }

  buscaRacas() {
    this.personagemService.buscarListaRaca().subscribe((response) => {
      this.listaRacas = response;
    });
  }

  buscaTendencias() {
    this.personagemService.buscarListaTendencia().subscribe((response) => {
      this.listaTendencias = response;
    });
  }

  inicializaCombos() {
    this.buscaUsuarios();
    this.buscaSexos();
    this.buscaClasses();
    this.buscaRacas();
    this.buscaTendencias();
  }

  inicializaDropDowns() {
    this.inicializaDropDownsTendencias();
    this.inicializaDropDownsGenerico();
  }

  inicializaDropDownsGenerico() {
    this.selectedItemsUsuarios = [];
    this.selectedItemsSexos = [];
    this.selectedItemsClasses = [];
    this.selectedItemsRacas = [];
    this.selectedItemsTendencias = [];

    this.dropdownSettingsGenerico = {
      singleSelection: false,
      idField: 'id',
      textField: 'nome',
      selectAllText: 'Selecionar todos',
      unSelectAllText: 'Desmarcar todos',
      itemsShowLimit: 1,
      allowSearchFilter: true,
      searchPlaceholderText: 'Pesquisar',
      noDataAvailablePlaceholderText: 'Nenhum registro encontrado'
    };
  }

  inicializaDropDownsTendencias() {
    this.selectedItemsTendencias = [];
    this.dropdownSettingsTendencias = {
      singleSelection: false,
      idField: 'id',
      textField: 'descricao',
      selectAllText: 'Selecionar todos',
      unSelectAllText: 'Desmarcar todos',
      itemsShowLimit: 1,
      allowSearchFilter: true,
      searchPlaceholderText: 'Pesquisar',
      noDataAvailablePlaceholderText: 'Nenhum registro encontrado'
    };
  }

  excluirPersonagem(personagem: any) {

    Swal.fire({
      title: `Você realmente deseja excluir o personagem?`,
      showDenyButton: false,
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Excluir',
      confirmButtonColor: '#ff1100',
    }).then((result) => {
      if (result.isConfirmed) {
        this.personagemService.removerPersonagem(personagem).subscribe((response) => {
          if (response) {
            this.listaPersonagens = response;
            this.fecharModalNovoPersonagem();
            this.notificacaoSucesso("Personagem removido com sucesso.");
            setTimeout(() => {
              this.executarPesquisaByFiltro();
            }, 50);
          } else {
            console.log(response)
            this.mensagemError("Erro ao remover personagem.")
          }
        });
      }
    })




  }

  abrirModalPersonagem() {
    this.modalVisualizarPersonagem.show();
  }

  fecharModalPersonagem() {
    this.modalVisualizarPersonagem.hide();
  }

  // MODAL EDITAR


  openModalVisualizarPersonagem(personagem: any) {
    this.personagemEditar = {
      id: personagem.id,
      nome: personagem.nome,
      raca: personagem.raca,
      classe: personagem.classe,
      tendencia: personagem.tendencia,
      fortitude: personagem.fortitude,
      reflexo: personagem.reflexo,
      vontade: personagem.vontade,
      sexo: personagem.sexo,
      forca: personagem.forca,
      destreza: personagem.destreza,
      constituicao: personagem.constituicao,
      inteligencia: personagem.inteligencia,
      sabedoria: personagem.sabedoria,
      carisma: personagem.carisma,
      classeArmadura: personagem.classeArmadura,
      vidaTotal: personagem.vidaTotal,
      vidaAtual: personagem.vidaAtual
    }
    this.modalVisualizarPersonagem.show();

  }

  // INICIO CORPO A CORPO
  openModalCorpoCorpo() {
    this.modalVisualizarPersonagem.hide();
    this.modalCorpoCorpo.show();
  }

  calculaTotalCorpoCorpo(){
    return this.personagemEditar.classe.pontosBaseAtaque + this.getModificador(this.personagemEditar.forca) + 0 + 0 ;
  }
  // FIM CORPO A CORPO

  // INICIO DISTANCIA
  openModalDistancia() {
    this.modalVisualizarPersonagem.hide();
    this.modalVisualizarDistancia.show();
  }

  calculaTotalDistancia(){
    return this.personagemEditar.classe.pontosBaseAtaque + this.getModificador(this.personagemEditar.destreza) + 0 + 0 ;
  }
  // FIM DISTANCIA

  // INICIO ARMAS

  openModalArmas(idPersonagem: any) {
    this.modalVisualizarPersonagem.hide();
    this.personagemService.buscarListaArmas(idPersonagem).subscribe((response) => {
      this.listaArmas = response;
    });

    this.modalArmas.show();
  }

  // FIM ARMAS


  // INICIO ARMADURAS

  openModalArmaduras(idPersonagem: any) {
    this.modalVisualizarPersonagem.hide();
    this.personagemService.buscarListaArmaduras(idPersonagem).subscribe((response) => {
      this.listaArmaduras = response;
    });

    this.modalArmaduras.show();
  }

  // FIM ARMADURA

  // INICIO ESCUDO

  openModalEscudos(idPersonagem: any) {
    this.modalVisualizarPersonagem.hide();
    this.personagemService.buscarListaEscudos(idPersonagem).subscribe((response) => {
      this.listaEscudos = response;
    });

    this.modalEscudos.show();
  }
  // FIM ESCUDO

  // INICIO HABILIDADES 

  openModalHabilidades(personagem: any) {
    console.log(personagem)
    this.personagemService.buscarListaHabilidadeClasse(personagem.classe).subscribe((response) => {
      this.listaHabilidadesClasse = response;
    });

    this.personagemService.buscarListaHabilidadeRaca(personagem.raca).subscribe((response) => {
      this.listaHabilidadesRaca = response;
    });

    this.modalVisualizarHabilidades.show();
  }

  // FIM HABILIDADES

  openModalPericias(idPersonagem: any) {
    this.modalVisualizarPersonagem.hide();
    this.personagemService.buscarListaPericias(idPersonagem).subscribe((response) => {
      this.listaPericias = response;
    });

    this.modalVisualizarPericia.show();
  }

  openModalUparPersonagem(personagem: any) {
    this.modalUparPersonagem.show();
  }



  openModalNovoPersonagem() {
    this.personagemEditar = {
      id: 0,
      nome: '',
      raca: { id: 0, nome: '' },
      classe: { id: 0, nome: "",  pontosBaseVida: 0, pontosBaseAtaque: 0  },
      tendencia: { id: 0, nome: '' },
      fortitude: 0,
      reflexo: 0,
      vontade: 0,
      sexo: '',
      forca: 0,
      destreza: 0,
      constituicao: 0,
      inteligencia: 0,
      sabedoria: 0,
      carisma: 0,
      classeArmadura: 0,
      vidaTotal: 0,
      vidaAtual: 0
    }
    this.modalNovoPersonagem.show();
  }

  salvarNovoPersonagem() {


    console.log(this.personagemEditar)
    this.personagemService.salvarPersonagem(this.personagemEditar).subscribe((response) => {
      if (response) {
        this.listaPersonagens = response;
        this.fecharModalNovoPersonagem();
        this.notificacaoSucesso("Personagem salvo com sucesso.");
        setTimeout(() => {
          this.executarPesquisaByFiltro();
        }, 50);
      } else {
        console.log(response)
        this.mensagemError("Erro ao salvar personagem.")
      }
    });


  }

  fecharModalNovoPersonagem() {
    this.modalNovoPersonagem.hide();
  }


  fazerRolagem() {
    this.personagemEditar.forca = Math.floor(Math.random() * 20) + 1;
    this.personagemEditar.destreza = Math.floor(Math.random() * 20) + 1;
    this.personagemEditar.constituicao = Math.floor(Math.random() * 20) + 1;
    this.personagemEditar.inteligencia = Math.floor(Math.random() * 20) + 1;
    this.personagemEditar.sabedoria = Math.floor(Math.random() * 20) + 1;
    this.personagemEditar.carisma = Math.floor(Math.random() * 20) + 1;

    this.calcularPontosDeVidaBase();
    this.calcularResistencias();
    this.calcularClasseArmadura();
    this.calcularAtaqueCorpoCorpo();
  }

  calcularAtaqueCorpoCorpo(){
    const ataqueCorpoCorpo = this.personagemEditar.classe.pontosBaseAtaque +  this.getModificador(this.personagemEditar.forca) + 0 + 0;
  }

  calcularResistencias(){
    this.personagemEditar.fortitude = 1 + this.getModificador(this.personagemEditar.constituicao);
    this.personagemEditar.reflexo = 1 + this.getModificador(this.personagemEditar.destreza);
    this.personagemEditar.vontade = 1 + this.getModificador(this.personagemEditar.sabedoria);
  }

  calcularClasseArmadura(){
    this.personagemEditar.classeArmadura = 10 + 1 + this.getModificador(this.personagemEditar.destreza);
  }

  calcularPontosDeVidaBase() {
    let pontosDeVidaBase = 0;
    console.log(this.personagemEditar.classe.nome)
    switch (this.personagemEditar.classe.nome) {
      case "Guerreiro":
        pontosDeVidaBase = 20;
        break;
      case "Mago":
        pontosDeVidaBase = 12;
        break;
      case "Clérigo":
        pontosDeVidaBase = 16;
        break;
      case "Ladino":
        pontosDeVidaBase = 14;
        break;
      case "Bárbaro":
        pontosDeVidaBase = 24;
        break;
      case "Paladino":
        pontosDeVidaBase = 20;
        break;
      case "Arqueiro":
        pontosDeVidaBase = 16;
        break;
      case "Patrulheiro":
        pontosDeVidaBase = 18;
        break;
      case "Feiticeiro":
        pontosDeVidaBase = 12;
        break;
      case "Nobre":
        pontosDeVidaBase = 14;
        break;
      case "Monge":
        pontosDeVidaBase = 16;
        break;
      case "Druida":
        pontosDeVidaBase = 14;
        break;
      case "Bardo":
        pontosDeVidaBase = 14;
        break;
      case "Psionista":
        pontosDeVidaBase = 12;
        break;
      case "Artífice":
        pontosDeVidaBase = 12;
        break;
      case "Xamã":
        pontosDeVidaBase = 16;
        break;
      default:
        console.log("Classe de personagem desconhecida.");
    }
    const pontos = pontosDeVidaBase + this.getModificador(this.personagemEditar.constituicao);
    this.personagemEditar.vidaAtual = pontos;
    this.personagemEditar.vidaTotal = pontos;
  }


  getModificador(valor: any) {
    if (valor >= 10) {
      return Math.floor((valor - 10) / 2);
    } else {
      return 0;
    }
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

}
