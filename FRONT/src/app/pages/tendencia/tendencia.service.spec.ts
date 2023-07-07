import { TestBed } from '@angular/core/testing';

import { TendenciaService } from './tendencia.service';

describe('TendenciaService', () => {
  let service: TendenciaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TendenciaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
