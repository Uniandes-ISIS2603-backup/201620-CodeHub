/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("usuarioModule", ["ngMessages","ui.router"]);
    mod.constant("usuarioContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/usuario/'; 
            $urlRouterProvider.otherwise("/usuario");
     
            $stateProvider.state('usuario', {
                url: '/usuario/{login}/{password}',
                param: {'login' : null,
                        'password' : null},
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
            }).state('registro', {
                url: '/usuario/registro',
                views: {
                    'hijoView': {
                        controller: 'registroCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath +'registrar.html'
                    }
                }});
        }]);
})(window.angular);