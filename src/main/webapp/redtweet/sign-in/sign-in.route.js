/**
 * Created by fx on 26/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('RedTweet')
        .config(signinRoute);

    signinRoute.$inject = ['$stateProvider'];

    function signinRoute($stateProvider) {
        $stateProvider
            .state('sign-in', {
                url: '/sign-in',
                templateUrl: 'redtweet/sign-in/sign-in.html',
                controller: 'SignInController as signInCtrl'
            });
    }

})();