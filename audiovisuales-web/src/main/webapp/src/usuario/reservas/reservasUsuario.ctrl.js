/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("reservasUsuarioModule");

    mod.controller("reservasUsuarioCtrl", ['$scope', '$state', '$stateParams', '$http', 'reservasUsuarioContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de ciudades está vacio
            $scope.records = {};
            // carga las ciudades
            if ($stateParams.usuarioId == undefined)
            {
                $stateParams.usuarioId = document.getElementById('idU').innerHTML;
            }
            $scope.idU = parseInt(document.getElementById('idU').innerHTML);
            if (sesion)
            {
                var loginKey = {login: $stateParams.login, password: $stateParams.password};
                $http.post("api/usuarios/login", loginKey)
                        .then(function (response) {
                            sesion = false;
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
                            document.getElementById('idU').innerHTML = usr.id;
                            var usuarioId = usr.id;
                            $http.get(context + "/" + usuarioId + "/reservas").then(function (response) {
                                $scope.records = response.data;
                            }, responseError);
                        }, responseError);
            } else
            {
                $http.get(context + "/" + $scope.idU + "/reservas")
                        .then(function (response) {
                    $scope.records = response.data;
                }, responseError);
            }


            // el controlador recibió un reservaId ??
            // revisa los parámetros (ver el :reservaId en la definición de la ruta)
            if ($stateParams.reservaId !== null && $stateParams.reservaId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.reservaId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        }, responseError);

                // el controlador no recibió un reservaId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    estado: '' /*Tipo String*/,
                    fechaInicial: '',
                    fechaFinal: '',
                    calificacion: 0.0,
                    generoSancion: false,
                    equipo: '',
                };

                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                // si el id es null, es un registro nuevo, entonces lo crea
                var idUsuario = parseInt(document.getElementById('idU').innerHTML);
                console.log($scope.currentRecord);
                if (id == null) {
                    $http.get("api/edificios/" + $stateParams.edificioId + "/equipos/" + $stateParams.equipoId)
                            .then(function (response) {
                                var equipo = response.data;
                                $scope.currentRecord.id = 1;
                                $scope.currentRecord.equipo = equipo;
                                $scope.currentRecord.calificacion = 0.0;
                                $scope.currentRecord.generoSancion = false;
                                var inicial = $scope.currentRecord.fechaInicial;
                                var strInicial = inicial.getDate()+"/"+(inicial.getMonth()+1)+"/"+inicial.getFullYear();
                                $scope.currentRecord.fechaInicial = strInicial;
                                var final = $scope.currentRecord.fechaFinal;
                                var strFinal = final.getDate()+"/"+(final.getMonth()+1)+"/"+final.getFullYear();
                                $scope.currentRecord.fechaFinal = strFinal;
                                return $http.post("api/usuarios/" + idUsuario + "/reservas", $scope.currentRecord)
                                        .then(function () {
                                        console.log(response.data);
                                            // $http.post es una promesa
                                            // cuando termine bien, cambie de estado
                                            $state.go('reservasUsuarioList('+idUsuario+')');
                                        }, responseError);
                            }, responseError);

                    // ejecuta POST en el recurso REST

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('reservasList');
                            }, responseError);
                }
                ;
            };

            this.editRecord = function (record) {
                $http.get("api/usuarios/" + $stateParams.usuarioId + "/reservas/" + $stateParams.reservaId)
                        .then(function (response) {
                            var reserva = response.data;
                            reserva.estado = record.estado;
                            reserva.calificacion = record.calificacion;
                            return $http.put(context + "/" + reserva.id, reserva)
                                    .then(function () {
                                        // $http.put es una promesa
                                        // cuando termine bien, cambie de estado
                                        $state.go('reservasUsuarioList(' + $stateParams.usuarioId + ')');
                                    }, responseError);
                        }, responseError);
                // ejecuta PUT en el recurso REST
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

