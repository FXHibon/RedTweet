/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('ProfileController', ProfileController);

    ProfileController.$inject = ['Tweet', '$state', '$rootScope', '$log'];

    function ProfileController(Tweet, $state, $rootScope, $log) {

        var me = this;

        me.tweets = [];


        // Init tweets list
        Tweet.getUserTimeLine($state.params.userName)
            .then(function (tweets) {
                me.tweets = tweets;
            })
            .catch(function (reason) {
                $log.error("unexpected error: ", reason);
            });

    }
})();
