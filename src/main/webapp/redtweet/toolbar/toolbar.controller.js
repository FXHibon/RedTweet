/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('ToolbarController', ToolbarController);

    ToolbarController.$inject = ['$rootScope', '$log', 'User', '$q', 'Search', '$state'];

    function ToolbarController($rootScope, $log, User, $q, Search, $state) {

        var me = this;

        me.searchText = "";
        me.isAuthenticated = isAuthenticated;

        me.root = $rootScope;

        me.logout = logout;
        me.querySearch = querySearch;
        me.selectedItemChange = selectedItemChange;

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

        function selectedItemChange(item) {
            if (item) {
                $state.go('profile', {userName: item.userName});
            }
        }

        function querySearch(query) {
            var deferred = $q.defer();

            Search
                .search({query: query})
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
