/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('ToolbarController', ToolbarController);

    ToolbarController.$inject = ['$rootScope', '$log', 'User', '$q', 'Search'];

    function ToolbarController($rootScope, $log, User, $q, Search) {

        var me = this;

        me.searchText = "";
        me.isAuthenticated = isAuthenticated;

        me.root = $rootScope;

        me.logout = logout;
        me.querySearch = querySearch;

        /////////////////////////////////////

        function isAuthenticated() {
            return $rootScope.user && $rootScope.user !== {};
        }

        function logout() {
            User.logout()
                .catch(function (reason) {
                    $log("error:", reason);
                });
        }

        function querySearch(query) {
            $log.info(query);
            var deferred = $q.defer();

            Search
                .one()
                .get({query: query})
                .then(function (result) {
                    deferred.resolve(result);
                })
                .catch(function (reason) {
                    $log.error(reason);
                });

            return deferred.promise;
        }
    }
})();
