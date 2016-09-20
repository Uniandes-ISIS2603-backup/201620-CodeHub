/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("equiposModule", ["ngMessages","ui.router"]);
    mod.constant("equiposContext", "api/edificios/");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/administrador/';
            $urlRouterProvider.otherwise("/equiposList");
     
            $stateProvider.state('equiposList', {
                url: '{edificioId:int}/equipos',
                parent: 'edificiosList',
                param: {'edificioId' : null},   
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos/equipos.list.html'
                    }
                }
            }).state('equiposCreate', {
                url: '/equipos/create',
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos/equipos.create.html'
                    }
                }

            }).state('equipoEdit', {
                url: '/equipos/:equipoCodigo',
                param: {
                    equipoCodigo: null
                },
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'equiposCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos/equipos.create.html'
                    }
                }
            });
        }]);
})(window.angular);