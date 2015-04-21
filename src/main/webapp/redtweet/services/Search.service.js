/**
 * Created by fhibon on 01/04/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('Search', TweetService);

    TweetService.$inject = ['Restangular'];

    function TweetService(Restangular) {
        return Restangular.service('search');

    }
})();