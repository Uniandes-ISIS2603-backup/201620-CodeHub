/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("reservasAdminModule", ["ngMessages","ui.router"]);
    mod.constant("reservasAdminContext", "api/reservas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/administrador/';
            $urlRouterProvider.otherwise("/reservasAdminList");
     
            $stateProvider.state('reservasAdminList', {
                url: '/usuario/{usuarioId:int}/reservas',
                param: {'usuarioId' : null},
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'reservasAdminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas/reservasAdmin.list.html'
                    }
                }
            }).state('reservasAdminCreate', {
                url: '/reservas/create',
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'reservasAdminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas/reservasUsuario.create.html'
                    }
                }

            }).state('reservaAdminEdit', {
                url: '/usuario/{usuarioId:int}/reservas/:reservaId',
                param: {
                    usuarioId: null,
                    reservaId: null
                },
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'reservasAdminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservas/reservasAdmin.create.html'
                    }
                }
            });
        }]);
})(window.angular);

