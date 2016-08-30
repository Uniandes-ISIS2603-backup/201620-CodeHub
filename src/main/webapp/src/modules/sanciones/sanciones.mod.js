/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("sancionesModule", ["ngMessages"]);
    mod.constant("sancionesContext", "api/sanciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/sanciones/';
            $urlRouterProvider.otherwise("/sancionesList");
     
            $stateProvider.state('sancionesList', {
                url: '/sanciona',
                views: {
                    'mainView': {
                        controller: 'sancionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sanciones.list.html'
                    }
                }
            }).state('sancionCreate', {
                url: '/sanciones/create',
                views: {
                    'mainView': {
                        controller: 'sancionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sanciones.create.html'
                    }
                }

            }).state('sancionEdit', {
                url: '/sanciones/:usancionId',
                param: {
                    cityId: null
                },
                views: {
                    'mainView': {
                        controller: 'sancionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sanciones.create.html'
                    }
                }
            });
        }]);
})(window.angular);

