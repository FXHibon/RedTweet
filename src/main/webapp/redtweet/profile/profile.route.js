/**
 * Created by fx on 26/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('RedTweet')
        .config(profileRoute);

    profileRoute.$inject = ['$stateProvider'];

    function profileRoute($stateProvider) {
        $stateProvider
            .state('profile', {
                url: '/profile/:userName',
                templateUrl: 'redtweet/profile/profile.html',
                controller: 'ProfileController as profileCtrl'
            });
    }

})();