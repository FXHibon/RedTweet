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

        me.submit = submit;

        me.user = $rootScope.user;
        // Init tweets list
        Tweet.getList()
            .then(function (tweets) {
                me.tweets = tweets;
            })
            .catch(function (reason) {
                $log.error("unexpected error: ", reason);
            });

        ///////////////////

        function submit() {
            $log.info("submitting ", me.tweet);
            Tweet.post(me.tweet)
                .then(function (tweet) {
                    $log.info("post OK");
                    me.tweets.push(tweet);
                })
                .catch(function (reason) {
                    $log.info("post KO", reason);
                });
        }


    }
})();
