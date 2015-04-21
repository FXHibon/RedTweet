/**
 * Created by fhibon on 01/04/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('Tweet', TweetService);

    TweetService.$inject = ['Restangular'];

    function TweetService(Restangular) {
        var userTimeline = Restangular.service("user_timeline");
        var homeTimeline = Restangular.service("home_timeline");

        return {
            getHomeTimeLine: getHomeTimeLine,
            submit: submit,
            getUserTimeLine: getUserTimeLine
        };

        function getHomeTimeLine() {
            return homeTimeline.getList();
        }

        function submit(tweet) {
            return homeTimeline.post(tweet);
        }

        function getUserTimeLine(user) {
            return userTimeline.getList({userName: user});
        }
    }
})();