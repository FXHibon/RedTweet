/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('TweetController', TweetController);

    TweetController.$inject = ['Tweet', '$scope'];

    function TweetController(Tweet, $scope) {

        $scope.retweet = retweet;
        $scope.favorite = favorite;

        /////////////////////////////////////

        function retweet() {
            console.log("retweet ", $scope.tweet);
            Tweet.retweet($scope.tweet);
        }

        function favorite() {
            console.log("favorite ", $scope.tweet);
        }
    }
})();
