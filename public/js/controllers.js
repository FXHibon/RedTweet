/**
 * Created by Fx on 25/02/2015.
 */

var controllers = angular.module('Controllers', ['ngMaterial']);

controllers.controller('SignInController', ['$scope',
    function ($scope) {
        $scope.user = {email: '', password: ''};
    }
]);