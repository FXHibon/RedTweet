/**
 * Created by fx on 26/03/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('User', UserService);

    UserService.$inject = ['Restangular'];

    function UserService(Restangular) {

        var usersRestClient = Restangular.service('auth');

        return {
            auth: function (user) {
                return usersRestClient
                    .one()
                    .get(user);
            }
        }
    }
})();