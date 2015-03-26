/**
 * Created by fx on 26/03/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .factory('Users', UserService);

    UserService.$inject = ['Restangular'];

    function UserService(Restangular) {

        var usersRestClient = Restangular.service('users');

        return {
            auth: function (user) {
                return usersRestClient
                    .one(user)
                    .get();
            }
        }
    }
})();