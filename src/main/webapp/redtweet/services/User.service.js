/**
 * Created by fx on 26/03/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('User', UserService);

    UserService.$inject = ['Restangular', '$state', '$rootScope'];

    function UserService(Restangular, $state, $rootScope) {

        var usersRestClient = Restangular.service('auth');
        var logoutRestClient = Restangular.service('logout');

        return {
            auth: function (user) {
                return usersRestClient
                    .one()
                    .get(user);
            },
            logout: function () {
                return logoutRestClient
                    .one()
                    .get()
                    .then(function () {
                        delete $rootScope.user;
                        $state.go("sign-in");
                    });
            }
        }
    }
})();