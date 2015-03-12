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
                templateUrl: '../views/sign-form.html',
                controller: 'SignController'
            })
            .when('/home', {
                templateUrl: '../views/board.html',
                controller: 'BoardController'
            })
            .otherwise({
                redirectTo: '/sign-in'
            });
    }
]);
