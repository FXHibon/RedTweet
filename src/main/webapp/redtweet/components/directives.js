/**
 * Created by Fx on 21/04/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .directive("rtTweetForm", rtTweetForm)
        .directive("rtTweet", rtTweet)
        .directive("rtUserCard", rtUserCard);

    function rtTweetForm() {
        return {
            restrict: 'E',
            templateUrl: "redtweet/components/tweet-form/tweet-form.html",
            controller: "TweetFormController",
            controllerAs: "tweetFormCtrl"
        };
    }

    function rtTweet() {
        return {
            restrict: 'E',
            templateUrl: "redtweet/components/tweet/tweet.html",
            scope: {
                tweet: '='
            }
        };
    }

    function rtUserCard() {
        return {
            restrict: 'E',
            templateUrl: "redtweet/components/user-card/user-card.html",
            scope: {
                user: '='
            }
        }
    }

})();