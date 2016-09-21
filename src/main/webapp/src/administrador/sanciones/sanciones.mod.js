/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("sancionesModule", ["ngMessages","ui.router"]);
    mod.constant("sancionesContext", "api/sanciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/administrador/';
            $urlRouterProvider.otherwise("/sancionesList");
     
            $stateProvider.state('sancionesList', {
                url: '{usuarioId:int}/sanciones',
                param: {'usuarioId' : null},
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'sancionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sanciones/sanciones.list.html'
                    }
                }
            }).state('sancionEdit', {
                url: '{usuarioId:int}/sanciones/{sancionId:int}',
                param: {'usuarioId' : null, 'sancionId' : null},
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'sancionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sanciones/sanciones.create.html'
                    }
                }});
        }]);
})(window.angular);

