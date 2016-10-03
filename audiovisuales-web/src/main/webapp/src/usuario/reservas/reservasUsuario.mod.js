/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("reservasUsuarioModule", ["ngMessages","ui.router"]);
    mod.constant("reservasUsuarioContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/usuario/';
            $urlRouterProvider.otherwise("/reservasUsuarioList");
     
            $stateProvider.state('reservasUsuarioList', {
                url: '{usuarioId:int}/reservas',
                param: {'usuarioId': null},
                views: {
                    'mainView': {
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuario.html'
                    },
                    'hijoView': {
                        controller: 'reservasUsuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas/reservasUsuario.list.html'
                    }
                }
            }).state('reservaUsuarioCreate', {
                url: '/reservas/create/{edificioId:int}/{equipoId:int}',
                param: { 'edificioId': null, 'equipoId' : null},
                views: {
                    'mainView': {
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuario.html'
                    },
                    'hijoView': {
                        controller: 'reservasUsuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas/reservaUsuario.create.html'
                    }
                }

            }).state('reservaUsuarioEdit', {
                url: '/reservas/:reservaId',
                param: {
                    reservaId: null
                },
                views: {
                    'mainView': {
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuario.html'
                    },
                    'hijoView': {
                        controller: 'reservasUsuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas/reservaUsuario.create.html'
                    }
                }
            });
        }]);
})(window.angular);

