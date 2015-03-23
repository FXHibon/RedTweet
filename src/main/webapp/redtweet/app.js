(function () {
    angular
        .module('RedTweet', [
            'ngMaterial',
            'ngRoute',
            'Controllers'
        ])
        .config(appConfig);

    appConfig.$inject = ['$mdThemingProvider', '$routeProvider'];

    function appConfig($mdThemingProvider, $routeProvider) {

        $mdThemingProvider.theme('default')
            .primaryPalette('blue');

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

})();
