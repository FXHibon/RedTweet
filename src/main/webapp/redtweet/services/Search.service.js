/**
 * Created by fhibon on 01/04/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('Search', SearchService);

    SearchService.$inject = ['$resource'];

    function SearchService($resource) {
        return $resource('api/search');

    }
})();