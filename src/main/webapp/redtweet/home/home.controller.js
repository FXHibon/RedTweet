/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['Tweet', '$state', '$rootScope'];

    function HomeController(Tweet, $state, $rootScope) {

        var me = this;

        me.tweets = [];
        fetchTweets();

        ///////////////////

        function fetchTweets() {
            Tweet.getAll()
                .then(function (tweets) {
                    me.tweets = tweets;
                });
        }


    }
})();
