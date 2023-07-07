import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'duasCasasAposPonto'
})
export class DuasCasasAposPonto implements PipeTransform {

  // transform(value: unknown, ...args: unknown[]): unknown {
  //   return null;
  // }

  transform(value: any): any {
    //console.log(value);
    if (value !== undefined && value !== null) {
      return value.toFixed(2);
    } else {
      return "0";
    }
  }

}
