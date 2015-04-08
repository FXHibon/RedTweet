/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('ToolbarController', ToolbarController);

    ToolbarController.$inject = ['$state', '$rootScope', '$log'];

    function ToolbarController($state, $rootScope, $log) {

        var me = this;

        me.isAuthenticated = isAuthenticated;

        me.root = $rootScope;

        /////////////////////////////////////

        function isAuthenticated() {
            return $rootScope.user && $rootScope.user !== {};
        }
    }
})();
