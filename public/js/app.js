var app = angular.module('RedTweet', ['ngMaterial', 'ngRoute', 'Controllers']);

app.config(['$mdThemingProvider',
    function ($mdThemingProvider) {
        $mdThemingProvider.theme('default')
            .primaryPalette('blue');
    }
]);

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/sign-in', {
                templateUrl: '../views/sign-in-form.html',
                controller: 'SignInController'
            })
            .when('/sign-up', {
                templateUrl: '../views/sign-up-form.html',
                controller: 'SignUpController'
            })
            .when('/home', {
                templateUrl: '../views/home.html',
                controller: 'HomeController'
            })
            .otherwise({
                redirectTo: '/sign-in'
            });
    }
]);
