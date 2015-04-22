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
            remove: remove,
            getHomeTimeLine: getHomeTimeLine,
            submit: submit,
            getUserTimeLine: getUserTimeLine,
            retweet: retweet,
            favorite: favorite,
            unfavorite: unfavorite
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
            return retweetResource
                .save({id: tweet.id}, tweet)
                .$promise;
        }

        function favorite(tweet) {
            return $resource('api/favorite/:id')
                .save({id: tweet.id}, {})
                .$promise;
        }

        function remove(tweet) {
            return $resource('api/destroy/:id')
                .save({id: tweet.id}, tweet)
                .$promise;
        }

        function unfavorite(tweet) {
            return $resource('api/unfavorite/:id')
                .save({id: tweet.id}, {})
                .$promise;
        }
    }
})();