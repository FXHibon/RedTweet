module.exports = function (router) {

    /**
     * createClient(port, host)
     */
    var redisClient = require('redis').createClient(6379, "127.0.0.1");
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
        redisClient.hget('users', user.name, apiDebug);

        var status = 200;
        var objResponse = {};

        // User not found
        if (!userId) {
            status = 401;
            objResponse.cause = "name";
        } else {
            var password = redisClient.hget('user:' + userId, 'password', apiDebug);
            if (password !== user.password) {
                status = 401;
                objResponse.cause = "password";
            } else {
                var cookie = redisClient.hget('user:' + userId, 'auth', apiDebug);
                res.cookie('auth', cookie);
            }
        }

        res.status(status).json(objResponse);
    });

};
