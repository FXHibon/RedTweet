module.exports = function (router) {

    var redisAccess = require('redis');
    var debug = require('debug')('RedTweet:server');

    /*******************
     *  API's routes
     *******************/

    /**
     * Handle authentication
     */
    router.post('/auth', function (req, res, next) {
        res.json({data: "coucou"});
    });

};