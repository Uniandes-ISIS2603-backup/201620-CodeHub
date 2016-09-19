/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("equiposModule", ["ngMessages","ui.router"]);
    mod.constant("equiposContext", "api/equipos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/equipos/';
            $urlRouterProvider.otherwise("/equiposList");
     
            $stateProvider.state('equiposList', {
                url: '/equipos',
                views: {
                    'mainView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos.list.html'
                    }
                }
            }).state('equiposCreate', {
                url: '/equipos/create',
                views: {
                    'mainView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos.create.html'
                    }
                }

            }).state('equipoEdit', {
                url: '/equipos/:equipoCodigo',
                param: {
                    equipoCodigo: null
                },
                views: {
                    'mainView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos.create.html'
                    }
                }
            });
        }]);
})(window.angular);