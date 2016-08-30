/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

var mod = ng.module("mainApp", ["ui.router","usuariosModule","edificiosModule",
    "equiposModule","sancionesModule","adminModule","reservasModule","ngMessages"]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/usuariosList');
        }]);
    
    })(window.angular);