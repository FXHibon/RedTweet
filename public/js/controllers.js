/**
 * Created by Fx on 25/02/2015.
 */

var controllers = angular.module('Controllers', ['ngMaterial', 'RedApi', 'ngMessages']);


controllers.controller('SignInController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {
        $scope.user = {name: '', password: ''};

        $scope.authenticating = false;

        $scope.submit = function () {
            $scope.authenticating = true;
            $http.post('/api/auth', $scope.user)
                .success(function () {
                    $location.url('/home');
                })
                .error(function (data, status) {
                    $scope.authenticating = false;
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

controllers.controller('SignUpController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {
        $scope.user = {
            name: '',
            email: '',
            password: '',
            passwordBis: ''
        };

        $scope.authenticating = false;

        $scope.submit = function () {
            $scope.authenticating = true;
            $http.post('/api/suscribe', $scope.user)
                .success(function () {
                    $location.url('/home');
                })
                .error(function (data, status) {
                    $scope.authenticating = false;


                    if (data.cause === "name") {
                        $scope.signUpForm.name.$error.nameNotAvailable = true;
                    } else {
                        delete $scope.signUpForm.name.$error.nameNotAvailable;
                    }

                    if (data.cause === "password") {
                        $scope.signUpForm.password.$error.badPassword = true;
                    } else {
                        delete $scope.signUpForm.password.$error.badPassword;
                    }

                    if (data.cause === "email") {
                        $scope.signUpForm.email.$error.emailNotAvailable = true;
                    } else {
                        delete $scope.signUpForm.email.$error.emailNotAvailable;
                    }
                });
        };
    }
]);

controllers.controller('HomeController', ['$scope',
    function ($scope) {

    }
]);