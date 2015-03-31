/**
 * Created by Fx on 25/02/2015.
 */

(function () {
    'use strict';

    angular
        .module('RedTweet')
        .controller('SignInController', SignInController);

    SignInController.$inject = ['User', '$state', '$rootScope', '$mdDialog'];

    function SignInController(UserService, $state, $rootScope, $mdDialog) {

        var me = this;

        me.requesting = false;

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
                    $mdDialog.show(
                        $mdDialog.alert()
                            .title('Connexion error')
                            .content('Invalid credentials')
                            .ok('Ok!'));
                });
        }
    }
})();
