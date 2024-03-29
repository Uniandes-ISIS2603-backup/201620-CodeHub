/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("usuariosModule", ["ngMessages","ui.router"]);
    mod.constant("usuariosContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/administrador/';
            $urlRouterProvider.otherwise("/usuariosList");
     
            $stateProvider.state('usuariosList', {
                url: '/usuarios',
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'usuariosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuarios/usuarios.list.html'
                    }
                }
            });
        }]);
})(window.angular);