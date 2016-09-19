/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("reservasAdminModule", ["ngMessages","ui.router"]);
    mod.constant("reservasAdminContext", "reservas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/templates/administrador/reservas/';
            $urlRouterProvider.otherwise("/reservasAdminList");
     
            $stateProvider.state('reservasAdminList', {
                url: '/reservas',
                views: {
                    'hijoView': {
                        controller: 'reservasAdminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservasAdmin.list.html'
                    }
                }
            }).state('reservasAdminCreate', {
                url: '/reservas/create',
                views: {
                    'hijoView': {
                        controller: 'reservasAdminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservasUsuario.create.html'
                    }
                }

            }).state('reservaAdminEdit', {
                url: '/reservas/:reservaId',
                param: {
                    reservaId: null
                },
                views: {
                    'hijoView': {
                        controller: 'reservasAdminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservasAdmin.create.html'
                    }
                }
            });
        }]);
})(window.angular);

