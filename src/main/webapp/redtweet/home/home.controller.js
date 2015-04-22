/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['Tweet', '$state', '$rootScope', '$log'];

    function HomeController(Tweet, $state, $rootScope, $log) {

        var me = this;

        me.tweets = [];

        me.user = $rootScope.user;

        $rootScope.$on('deleteTweet', function (event, tweetId) {
            me.tweets = me.tweets.filter(function (item) {
                return (item.id !== tweetId);
            })
        });

        $rootScope.$on("addTweet", function (event, tweet) {
            $log.info(event, tweet);
            me.tweets.push(tweet);
        });

        // Init tweets list
        Tweet.getHomeTimeLine()
            .then(function (tweets) {
                me.tweets = tweets;
            })
            .catch(function (reason) {
                $log.error("unexpected error: ", reason);
            });

    }
})();
