/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('TweetController', TweetController);

    TweetController.$inject = ['Tweet', '$scope', '$rootScope'];

    function TweetController(Tweet, $scope, $rootScope) {

        $scope.retweet = retweet;
        $scope.favorite = favorite;
        $scope.user = $rootScope.user;
        $scope.delete = deleteTweet;

        /////////////////////////////////////

        function retweet() {
            console.log("retweet ", $scope.tweet);
            Tweet.retweet($scope.tweet)
                .then(function () {
                    $scope.tweet.retweeted = true;
                });
        }

        function favorite() {

            if ($scope.tweet.favorite) {
                Tweet.unfavorite($scope.tweet)
                    .then(function () {
                        $scope.tweet.favorite = false;
                    });
            } else {
                Tweet.favorite($scope.tweet)
                    .then(function () {
                        $scope.tweet.favorite = true;
                    });
            }
        }

        function deleteTweet() {
            Tweet.remove($scope.tweet)
                .then(function () {
                    $rootScope.$broadcast("deleteTweet", $scope.tweet.id);
                });
        }
    }
})();
        