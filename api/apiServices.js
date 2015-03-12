/**
 * Created by fx on 26/02/2015.
 */

/*
 * Redis data structure:
 * STRING newUser   // use to get new used id
 * STRING newTweet  //  use to get new tweet id
 * HASH users [key=userName] [value=userId] // Contains user information
 * HASH user:[userId] [key=user.key] [val]      // Here lies 'password' and 'auth'
 * HASH auths [key=authVal] [val=userId]
 */

/**
 * createClient(port, host)
 */
var redisClient = require('redis').createClient(6379, "192.168.248.132");
var async = require('async');
var uuid = require('node-uuid');
var apiDebug = require('debug')('RedTweet:api');

var redisDebug = require('debug')('RedTweet:redis');

redisClient.on('error', function (err) {
    redisDebug("Error: ", err);
});

/**
 * Authenticate (or not) the given user
 * @param tmpUser {Object} User the authenticate
 * @param httpResponse To send response
 */
module.exports.authenticate = function (tmpUser, httpResponse) {
    var user = tmpUser;
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
            var authId = uuid();
            redisClient.hset(['auths', authId, userId]);
            callback(null, authId);
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
            httpResponse.cookie('auth', cookieVal);
        }
        httpResponse.status(status).json(objResponse);
    });
};

/**
 * record a new user
 * @param user User to record
 * @param resp HTTP Response
 */
module.exports.suscribe = function (userTmp, httpResponse) {
    var user = userTmp;
    var checkUserNameAvailability = function (callback) {
        redisClient.hget(['users'], callback);
    };
    var checkEmailAvailability;
    var checkPasswordMatch;

    async.waterfall([
            checkUserNameAvailability,
            checkEmailAvailability,
            checkPasswordMatch
        ],
        function (err, result) {
            if (!err) {
                httpResponse.status(200).json();
            } else {
                httpResponse.status(400).json({cause: err});
            }
        });
}