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
            Tweet.retweet($scope.tweet);
        }

        function favorite() {
            console.log("favorite ", $scope.tweet);
        }

        function deleteTweet() {
            Tweet.remove($scope.tweet)
                .success(function () {
                    // TODO delete tweet from client list
                });
        }
    }
})();
        