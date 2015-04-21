/**
 * Created by fhibon on 01/04/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('Search', SearchService);

    SearchService.$inject = ['Restangular'];

    function SearchService(Restangular) {
        return Restangular.service('search');

    }
})();