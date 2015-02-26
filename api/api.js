module.exports = function (router) {
    /**
     * createClient(port, host)
     */
    var redisClient = require('redis').createClient(6379, "192.168.248.129");
    var async = require('async');
    var apiDebug = require('debug')('RedTweet:api');
    var redisDebug = require('debug')('RedTweet:redis');

    redisClient.on('error', function (err) {
        redisDebug("Error: ", err);
    });

    /*******************
     *  API's routes
     *******************/

    /**
     * Handle authentication
     */
    router.post('/auth', function (req, res, next) {
        var user = req.body;

        var objResponse = {};
        var userId;
        var status = 200;

        var getLogin = function (callback) {
            redisClient.hget(['users', user.name], callback);
        };

        var getPassword = function (id, callback) {
            if (id) {
                userId = id;
                redisClient.hget(['user:' + id, 'password'], callback);
            } else {
                callback('name');
            }
        };

        var getCookie = function (password, callback) {
            if (password && password === user.password) {
                redisClient.hget('user:' + userId, 'auth', callback);
            } else {
                callback('password');
            }
        };

        async.waterfall([getLogin, getPassword, getCookie], function (err, cookieVal) {
            if (err) {
                apiDebug("error: ", err);
                status = 401;
                objResponse.cause = err;
            } else {
                apiDebug("ok: cookie:", cookieVal);
                status = 200;
                res.cookie('auth', cookieVal);
            }
            res.status(status).json(objResponse);
        });
    });

};
