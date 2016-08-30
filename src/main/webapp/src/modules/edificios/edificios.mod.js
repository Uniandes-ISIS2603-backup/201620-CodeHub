/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("edificiosModule", ["ui.router"]);
    mod.constant("edificiosContext", "api/edificios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/edificios/';
            $urlRouterProvider.otherwise("/edificiosList");
     
            $stateProvider.state('edificiosList', {
                url: '/edificios',
                views: {
                    'mainView': {
                        controller: 'edificiosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'edificios.list.html'
                    }
                }
            }).state('edificiosCreate', {
                url: '/edificios/create',
                views: {
                    'mainView': {
                        controller: 'edificiosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'edificios.create.html'
                    }
                }

            }).state('edificioEdit', {
                url: '/edificios/:edificioId',
                param: {
                    edificioId: null
                },
                views: {
                    'mainView': {
                        controller: 'edificiosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'edificios.create.html'
                    }
                }
            });
        }]);
})(window.angular);

