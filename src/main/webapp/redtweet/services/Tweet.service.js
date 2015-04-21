/**
 * Created by fhibon on 01/04/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('Tweet', TweetService);

    TweetService.$inject = ['Restangular', '$resource', '$log'];

    function TweetService(Restangular, $resource, $log) {
        var userTimeline = Restangular.service("user_timeline");
        var homeTimeline = Restangular.service("home_timeline");

        var retweetResource = $resource("api/retweet/:id");

        return {
            getHomeTimeLine: getHomeTimeLine,
            submit: submit,
            getUserTimeLine: getUserTimeLine,
            retweet: retweet,
            favorite: favorite
        };

        function getHomeTimeLine() {
            return homeTimeline.getList();
        }

        function submit(tweet) {
            return homeTimeline.post(tweet);
        }

        function getUserTimeLine(user) {
            return userTimeline
                .one()
                .get({userName: user});
        }

        function retweet(tweet) {
            retweetResource.save({id: tweet.id}, tweet);
        }

        function favorite(tweet) {
            $log.info("asking favorite for ", tweet);
        }
    }
})();