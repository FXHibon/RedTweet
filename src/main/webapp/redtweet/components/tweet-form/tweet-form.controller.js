/**
 * Created by Fx on 19/04/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('TweetFormController', TweetFormController);

    TweetFormController.$inject = ['Tweet', '$rootScope', '$log'];

    function TweetFormController(Tweet, $rootScope, $log) {

        var me = this;

        me.submit = submit;

        //////////////////////////////////////

        function submit() {
            $log.info("submitting ", me.tweet);
            Tweet.submit(me.tweet)
                .then(function (tweet) {
                    $log.info("post OK");
                    me.tweet = {};
                    $rootScope.$broadcast("addTweet", tweet);
                })
                .catch(function (reason) {
                    $log.error("submit KO =>", reason);
                });
        }
    }
})();