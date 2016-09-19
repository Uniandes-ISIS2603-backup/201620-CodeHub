/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("sancionesModule", ["ngMessages","ui.router"]);
    mod.constant("sancionesContext", "api/sanciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/usuario/sanciones/';
            $urlRouterProvider.otherwise("/sancionesList");
     
            $stateProvider.state('sancionesList', {
                url: '/sanciones',
                views: {
                    'mainView': {
                        controller: 'sancionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sanciones.list.html'
                    }
                }
            });
        }]);
})(window.angular);

