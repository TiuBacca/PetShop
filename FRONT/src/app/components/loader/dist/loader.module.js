"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
exports.LoaderModule = void 0;
var core_1 = require("@angular/core");
var common_1 = require("@angular/common");
var ngx_loading_1 = require("ngx-loading");
var loader_component_1 = require("./loader.component");
var LoaderModule = /** @class */ (function () {
    function LoaderModule() {
    }
    LoaderModule = __decorate([
        core_1.NgModule({
            declarations: [loader_component_1.LoaderComponent],
            exports: [loader_component_1.LoaderComponent],
            imports: [
                common_1.CommonModule,
                ngx_loading_1.NgxLoadingModule.forRoot({
                    fullScreenBackdrop: true
                })
            ]
        })
    ], LoaderModule);
    return LoaderModule;
}());
exports.LoaderModule = LoaderModule;
