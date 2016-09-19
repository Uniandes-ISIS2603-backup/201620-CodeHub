/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("adminModule", ["ngMessages","ui.router"]);
    mod.constant("adminContext", "api/admin");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/administradores/'; 
            $urlRouterProvider.otherwise("/admin");
     
            $stateProvider.state('admin', {
                url: '/admins',
                views: {
                    'mainView': {
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'admin.html'
                    },
                    'hijoView': {
                        controller: 'reservasAdminCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reservasAdmin.html'
                    }
                }
            });
        }]);
})(window.angular);