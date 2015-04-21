/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('ToolbarController', ToolbarController);

    ToolbarController.$inject = ['$state', '$rootScope', '$log', 'User'];

    function ToolbarController($state, $rootScope, $log, User) {

        var me = this;

        me.isAuthenticated = isAuthenticated;

        me.root = $rootScope;

        me.logout = logout;

        /////////////////////////////////////

        function isAuthenticated() {
            return $rootScope.user && $rootScope.user !== {};
        }

        function logout() {
            User.logout()
                .then(function () {
                    $state.go("sign-in");
                })
                .catch(function (reason) {
                    $log("error:", reason);
                });
        }
    }
})();
