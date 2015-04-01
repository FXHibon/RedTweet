/**
 * Created by fx on 26/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('RedTweet')
        .config(homeRoute);

    homeRoute.$inject = ['$stateProvider'];

    function homeRoute($stateProvider) {
        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: 'redtweet/home/home.html',
                controller: 'HomeController as homeCtrl'
            });
    }

})();