/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('ProfileController', ProfileController);

    ProfileController.$inject = ['Tweet', 'Search', '$state', '$rootScope', '$log'];

    function ProfileController(Tweet, Search, $state, $rootScope, $log) {

        var me = this;

        me.tweets = [];

        me.user = {};

        // Init tweets list
        Tweet.getUserTimeLine($state.params.userName)
            .then(function (data) {
                me.tweets = data.tweets;
                me.user = data.user;
            })
            .catch(function (reason) {
                $log.error("unexpected error: ", reason);
            });

    }
})();
