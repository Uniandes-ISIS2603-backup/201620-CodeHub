/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("sancionesModule", ["ngMessages","ui.router"]);
    mod.constant("sancionesContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/usuario/';
            $urlRouterProvider.otherwise("/sancionesList");
     
            $stateProvider.state('sancionesList', {
                url: '{idUsuario:int}/sanciones',
                views: {
                    'mainView': {
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuario.html'
                    },
                    'hijoView': {
                        controller: 'sancionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sanciones/sanciones.list.html'
                    }
                }
            });
        }]);
})(window.angular);

