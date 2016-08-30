/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("adminModule", ["ngMessages","ui.router"]);
    mod.constant("adminContext", "api/admin");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/admin/';
            $urlRouterProvider.otherwise("/adminList");
     
            $stateProvider.state('adminList', {
                url: '/admins',
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.list.html'
                    }
                }
            }).state('adminCreate', {
                url: '/admins/create',
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.create.html'
                    }
                }

            }).state('adminEdit', {
                url: '/admins/:adminId',
                param: {
                    cityId: null
                },
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.create.html'
                    }
                }
            });
        }]);
})(window.angular);