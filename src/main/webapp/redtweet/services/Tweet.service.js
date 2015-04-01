/**
 * Created by fhibon on 01/04/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('Tweet', TweetService);

    TweetService.$inject = ['Restangular', '$log'];

    function TweetService(Restangular, $log) {

        var tweetsRestClient = Restangular.service('tweets');

        return {
            getAll: getAll
        };

        function getAll() {
            return tweetsRestClient.getList();
        }
    }
})();