/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("sancionesAdminModule", ["ngMessages","ui.router"]);
    mod.constant("sancionesAdminContext", "api/sanciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/administrador/';
            $urlRouterProvider.otherwise("/sancionesList");
     
            $stateProvider.state('sancionesList', {
                url: '{idUsuario:int}/sanciones',
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'sancionesAdminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sanciones/sanciones.list.html'
                    }
                }
            });
        }]);
})(window.angular);

