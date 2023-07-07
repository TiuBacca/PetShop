import { TestBed } from '@angular/core/testing';

import { GuardaRotaService } from './guarda-rota.service';

describe('GuardaRotaService', () => {
  let service: GuardaRotaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuardaRotaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
