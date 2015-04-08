(function () {
    angular
        .module('RedTweet', [
            'ngMaterial',
            'ui.router',
            'restangular',
            'ngMessages'
        ])
        .config(themeConfig)
        .config(routeConfig)
        .config(restConfig);

    themeConfig.$inject = ['$mdThemingProvider'];
    routeConfig.$inject = ['$urlRouterProvider'];
    restConfig.$inject = ['RestangularProvider'];

    function themeConfig($mdThemingProvider) {
        $mdThemingProvider.theme('default')
            .primaryPalette('blue');
    }

    function routeConfig($urlRouterProvider) {
        $urlRouterProvider.otherwise('/sign-in');
    }

    function restConfig(RestangularProvider) {
        RestangularProvider.setBaseUrl('/api/');
    }

})();
