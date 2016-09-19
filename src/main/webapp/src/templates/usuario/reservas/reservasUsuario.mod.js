/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("reservasUsuarioModule", ["ngMessages","ui.router"]);
    mod.constant("reservasUsuarioContext", "reservas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/templates/usuario/reservas/';
            $urlRouterProvider.otherwise("/reservasUsuarioList");
     
            $stateProvider.state('reservasUsuarioList', {
                url: '/reservas',
                views: {
                    'usuarioView': {
                        controller: 'reservasUsuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservasUsuario.list.html'
                    }
                }
            }).state('reservasUsuarioCreate', {
                url: '/reservas/create',
                views: {
                    'usuarioView': {
                        controller: 'reservasUsuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservasUsuario.create.html'
                    }
                }

            }).state('reservaUsuarioEdit', {
                url: '/reservas/:reservaId',
                param: {
                    reservaId: null
                },
                views: {
                    'usuarioView': {
                        controller: 'reservasUsuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservasUsuario.create.html'
                    }
                }
            });
        }]);
})(window.angular);

