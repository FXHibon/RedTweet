var app = angular.module('RedTweet', ['ngMaterial', 'ngRoute', 'Controllers']);

app.config(['$mdThemingProvider',
    function ($mdThemingProvider) {
        $mdThemingProvider.theme('default')
            .primaryPalette('red');
    }
]);

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/signin', {
                templateUrl: '../views/sign-in-form.html',
                controller: 'SignInController'
            })
            .otherwise({
                redirectTo: '/signin'
            });
    }
]);
