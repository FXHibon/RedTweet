/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('SignInController', SignInController);

    SignInController.$inject = ['User', '$state', '$rootScope'];

    function SignInController(UserService, $state, $rootScope) {

        var me = this;

        me.requesting = false;

        me.errors = {};

        me.submit = submit;


        ///////////////////////

        function submit() {
            me.requeting = true;
            me.errors = {};
            UserService.auth(me.user)
                .then(function (user) {
                    $rootScope.user = user;
                    $state.go('home');
                })
                .catch(function () {
                    me.requeting = false;
                    me.errors.unauthorized = true;
                });
        }
    }
})();
