(function () {

    'use strict';

    angular
        .module('RedTweet', [
            'ngMaterial',
            'ui.router',
            'restangular',
            'ngMessages',
            'angularMoment',
            'ngMdIcons',
            'ngResource'
        ])
        .config(themeConfig)
        .config(routeConfig)
        .config(restConfig)
        .run(bootstrap);

    themeConfig.$inject = ['$mdThemingProvider'];
    routeConfig.$inject = ['$urlRouterProvider'];
    restConfig.$inject = ['RestangularProvider'];
    bootstrap.$inject = ['$http', '$rootScope', '$state', '$log'];

    function themeConfig($mdThemingProvider) {
        $mdThemingProvider.theme('default')
            .primaryPalette('blue');
    }

    function routeConfig($urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');
    }

    function restConfig(RestangularProvider) {
        RestangularProvider.setBaseUrl('/api/');
    }

    /**
     * Check we are either authenticated or in "signin" state
     * @param $http
     * @param $rootScope
     * @param $state
     * @param $log
     * @param $state
     * @param $log
     */
    function bootstrap($http, $rootScope, $state, $log) {


        $rootScope.$on("$stateChangeStart", function () {
            refresh();
        });

        function refresh() {
            $http.get('/api/refresh')
                .success(function (user) {
                    $rootScope.user = user;
                })
                .error(function () {
                    $state.go("sign-in");
                });
        }
    }


})();
