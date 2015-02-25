/**
 * Created by Fx on 25/02/2015.
 */

var controllers = angular.module('Controllers', ['ngMaterial', 'RedApi']);

controllers.controller('SignInController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {
        $scope.user = {email: '', password: ''};

        $scope.submit = function () {
            $http.post('/api/auth', $scope.user)
                .success(function () {
                    $location.url('/home');
                })
                .error(function (data, status) {
                    $scope.signInForm.email.$error.emailNotFound = true;
                    $scope.signInForm.password.$error.badPassword = true;
                    console.log(data, status);
                });
        };
    }
]);

controllers.controller('HomeController', ['$scope',
    function ($scope) {

    }
]);