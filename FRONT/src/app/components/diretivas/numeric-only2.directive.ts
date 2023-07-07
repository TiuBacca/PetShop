import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appNumericOnly2]'
})
export class NumericOnlyDirective2 {
  constructor(private el: ElementRef) { }

  @HostListener('keydown', ['$event']) onKeyDown(event: KeyboardEvent) {
    const input = event.key;

    const currentValue = this.el.nativeElement.value + input;
    if (Number(currentValue) > 999 || Number(currentValue) < 0) {
      event.preventDefault();
    }
  
    // Permitir apenas números de 0 a 100 e as teclas de navegação
    // if ((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105)
    //   && (event.keyCode !== 8) && (event.keyCode !== 9) && (event.keyCode !== 35)
    //   && (event.keyCode !== 36) && (event.keyCode !== 37) && (event.keyCode !== 39)
    //   && (event.keyCode !== 46) && (event.keyCode !== 190)) {
    //   event.preventDefault();
    // }
    // // Limitar o valor entre 0 e 1000
    // else {
    //   const currentValue = this.el.nativeElement.value + input;
    //   if (Number(currentValue) > 999 || Number(currentValue) < 0) {
    //     event.preventDefault();
    //   }
    // }
  }
}
