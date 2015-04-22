/**
 * Created by fx on 26/03/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('User', UserService);

    UserService.$inject = ['Restangular', '$state', '$rootScope', '$resource'];

    function UserService(Restangular, $state, $rootScope, $resource) {

        var usersRestClient = Restangular.service('auth');
        var logoutRestClient = Restangular.service('logout');
        var followResource = $resource("api/follow/:id");

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
            },
            follow: function (userName) {
                followResource.get({id: userName});
            }
        }
    }
})();