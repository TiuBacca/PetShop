"use strict";
exports.__esModule = true;
var testing_1 = require("@angular/core/testing");
var loader_interceptor_1 = require("./loader.interceptor");
describe('InterceptorService', function () {
    var service;
    beforeEach(function () {
        testing_1.TestBed.configureTestingModule({});
        service = testing_1.TestBed.inject(loader_interceptor_1.InterceptorService);
    });
    it('should be created', function () {
        expect(service).toBeTruthy();
    });
});
