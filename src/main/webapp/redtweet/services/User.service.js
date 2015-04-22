/**
 * Created by fx on 26/03/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('User', UserService);

    UserService.$inject = ['$state', '$rootScope', '$resource'];

    function UserService($state, $rootScope, $resource) {

        var userResource = $resource('api/auth');
        var logoutResource = $resource('api/logout');
        var followResource = $resource("api/follow/:id");

        return {
            auth: function (user) {
                return userResource
                    .get(user)
                    .$promise;
            },
            logout: function () {
                return logoutResource
                    .get()
                    .$promise
                    .then(function () {
                        delete $rootScope.user;
                        $state.go("sign-in");
                    });
            },
            follow: function (userName) {
                followResource.get({id: userName}, {});
            }
        }
    }
})();