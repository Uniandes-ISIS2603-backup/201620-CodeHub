(function (ng) {
    var mod = ng.module("equiposModule");

    mod.controller("equiposCtrl", ['$scope', '$state', '$stateParams', '$http', 'equiposContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de equipos está vacio
            $scope.records = {};
            // carga los equipos
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibió un equipoCodigo ??
            // revisa los parámetros (ver el :equipoCodigo en la definición de la ruta)
            if ($stateParams.equipoCodigo !== null && $stateParams.equipoCodigo !== undefined) {
                
                // toma el id del parámetro
                codigo = $stateParams.equipoCodigo;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + codigo)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un equipoCodigo
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    codigo: undefined /*Tipo int. El valor se asigna en el backend*/,
                    estado: '' /*Tipo String*/,
                };
              
                $scope.alerts = [];
            }


            this.saveRecord = function (codigo) {
                currentRecord = $scope.currentRecord;
                
                // si el codigo es null, es un registro nuevo, entonces lo crea
                if (codigo == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('equiposList');
                        }, responseError);
                        
                // si el codigo no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.codigo, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('equiposList');
                        }, responseError);
                };
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