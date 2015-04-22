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
        return {
            search: search
        };

        function search(query) {
            return $resource('api/search')
                .query(query)
                .$promise;
        }


    }
})();