(function (ng) {
    var mod = ng.module("adminModule");

    mod.controller("adminCtrl", ['$scope', '$state', '$stateParams', '$http', 'adminContext', function ($scope, $state, $stateParams, $http, context) {

          
            $scope.records = {};
            if($stateParams.adminId!==undefined)
            {
                document.getElementById('idU').innerHTML = $stateParams.adminId;
            }
            $scope.idU = parseInt(document.getElementById('idU').innerHTML)     
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
                if (id == null) {

                    return $http.post(context, currentRecord)
                        .then(function () {
                            
                            $state.go('adminList');
                        }, responseError);
                        
                } else {
                    
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                        .then(function () {

                            $state.go('adminList');
                        }, responseError);
                };
            };
            this.deleteRecord = function (id) 
            {
                currentRecord = $scope.currentRecord;
                if(id!=null)
                {            
                    // ejecuta delete en el recurso REST
                    return $http.delete(context + "/" + id,currentRecord)
                        .then(function () {
                            $scope.records = {};
                            $http.get(context).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('adminList');
                        }, responseError); 
                }
                };


            // -----------------------------------------------------------------
            // Funciones para manejra los mensajes en la aplicación


            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };

            var self = this;
            function responseError(response) {

                self.showError(response.data);
            }
        }]);

})(window.angular);
