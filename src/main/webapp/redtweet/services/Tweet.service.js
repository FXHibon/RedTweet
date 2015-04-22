/**
 * Created by fhibon on 01/04/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('Tweet', TweetService);

    TweetService.$inject = ['$resource', '$log'];

    function TweetService($resource, $log) {

        var homeTimeLineResource = $resource('api/home_timeline');
        var userTimeLineResource = $resource('api/user_timeline');
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
            return homeTimeLineResource
                .query()
                .$promise;
        }

        function submit(tweet) {
            return homeTimeLineResource
                .save(tweet)
                .$promise;
        }

        function getUserTimeLine(user) {
            return userTimeLineResource
                .get({userName: user})
                .$promise;
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