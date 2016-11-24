/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("equiposAdminModule", ["ngMessages","ui.router"]);
    mod.constant("equiposContext", "api/edificios/");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/administrador/';
            $urlRouterProvider.otherwise("/equiposAdminList");
     
            $stateProvider.state('equiposAdminList', {
                url: '/edificioAdmin/{adminId:int}/equipos',
                param: {'adminId' : null},   
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'equiposAdminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos/equiposAdmin.list.html'
                    }
                }
            }).state('equiposAdminCreate', {
                url: '/equipos/create',
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'equiposAdminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos/equiposAdmin.create.html'
                    }
                }

            }).state('equipoAdminEdit', {
                url: '/{edificioId:int}/equipos/{equipoId:int}',
                param: {
                    edificioId: null,
                    equipoId: null
                },
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'equiposAdminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipos/equiposAdmin.create.html'
                    }
                }
            });
        }]);
})(window.angular);