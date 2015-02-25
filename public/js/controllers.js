/**
 * Created by Fx on 25/02/2015.
 */

var controllers = angular.module('Controllers', ['ngMaterial', 'RedApi', 'ngMessages']);

controllers.controller('SignInController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {
        $scope.user = {name: '', password: ''};

        $scope.submit = function () {
            $http.post('/api/auth', $scope.user)
                .success(function () {
                    $location.url('/home');
                })
                .error(function (data, status) {
                    if (data.cause === "name") {
                        $scope.signInForm.name.$error.nameNotFound = true;
                    } else {
                        delete $scope.signInForm.name.$error.nameNotFound;
                    }

                    if (data.cause === "password") {
                        $scope.signInForm.password.$error.badPassword = true;
                    } else {
                        delete $scope.signInForm.password.$error.badPassword;
                    }
                });
        };
    }
]);

controllers.controller('HomeController', ['$scope',
    function ($scope) {

    }
]);