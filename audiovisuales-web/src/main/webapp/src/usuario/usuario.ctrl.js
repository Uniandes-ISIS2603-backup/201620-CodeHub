/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("usuarioModule");

    mod.controller("usuarioCtrl", ['$scope', '$state', '$stateParams', '$http', 'usuarioContext', function ($scope, $state, $stateParams, $http, context) {

            $scope.records = {};
            if ($stateParams.usuarioId !== undefined)
            {
                document.getElementById('idU').innerHTML = $stateParams.usuarioId;
            }
            $scope.idU = parseInt(document.getElementById('idU').innerHTML);

            if ($stateParams.usuarioId !== null && $stateParams.usuarioId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.usuarioId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            console.log(response.data);
                            $scope.currentRecord = response.data;
                            var usr = response.data;
                            var str = "Nombre: " + usr.name + "<br>Tipo: ";
                            if (usr.tipo == 1)
                            {
                                str += "Estudiante<br>";
                            } else
                            {
                                str += "Profesor<br>";
                            }
                            if (usr.tieneSancion == "true")
                            {
                                str += "Actualmente sancionado";
                            }
                            if (document.getElementById("infoUsuario") !== null) {
                                document.getElementById("infoUsuario").innerHTML = str;
                            } else
                            {
                                document.getElementById("nombre").innerHTML = "Nombre: " + usr.name + " ";
                                if (usr.tipo == 1)
                                {
                                    document.getElementById("tipo").innerHTML = "Tipo: Estudiante<br>";
                                } else
                                {
                                    document.getElementById("tipo").innerHTML = "Tipo: Profesor<br>";
                                }
                            }
                        }, responseError);

            } else
            {
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    tieneSancion: '',
                    tipo: ''
                };

                $scope.alerts = [];
            }

            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('usuarioList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('usuariosList');
                            }, responseError);
                }
                ;
            };


            this.deleteRecord = function (id) {
                currentRecord = $scope.currentRecord;
                if (id != null)
                {
                    // ejecuta delete en el recurso REST
                    return $http.delete(context + "/" + id, currentRecord)
                            .then(function () {
                                $scope.records = {};
                                $http.get(context).then(function (response) {
                                    $scope.records = response.data;
                                }, responseError);
                                $state.go('usuariosList');
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

