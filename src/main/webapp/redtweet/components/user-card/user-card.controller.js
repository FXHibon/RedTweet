/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('UserCardController', UserCardController);

    UserCardController.$inject = ['User', '$scope', '$state', '$log', '$rootScope'];

    function UserCardController(User, $scope, $state, $log, $rootScope) {

        var me = this;

        me.follow = follow;

        me.user = $rootScope.user;

        /////////////////////////////////////

        function follow() {
            User.follow($scope.user.userName);
        }
    }
})();
