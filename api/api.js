module.exports = function (router) {

    var apiServices = require('./apiServices');

    /*******************
     *  API's routes
     *******************/

    router.post('/suscribe', function (req, res, next) {
        apiServices.suscribe(req.body, res);
    });

    /**
     * Handle authentication
     */
    router.post('/auth', function (req, res, next) {
        var user = req.body;
        apiServices.authenticate(user, res);
    });

    /**
     * To use following methods, user must be authenticated
     * So let's check it out
     */
    router.use(function (req, res, next) {
        if (req.cookie && req.cookie.auth) {
            redisClient.hget(['auths', req.cookie.auth], function (err, reply) {
                if (!err && reply) {
                    req.userId = reply;
                    next();
                    return;
                }
            });
        }

        res.status(401).json({
            cause: "this method need to be authenticated",
            url: req.originalUrl,
            method: req.method,
            params: req.query,
            body: req.body
        });

    });

    router.get('/tweets/:userId/:begin/:count', function (req, res, next) {

        var params = req.params;

        async.waterfall(
            [
                function (callback) {
                    redisClient.lget(['tweets:' + params.userId, params.begin, params.begin + (params.count || 10)], callback);
                },

                function (tweetsIds, callback) {
                    tweetsIds.forEach(function (tweetId) {

                    });
                }
            ],
            function (err, res) {
                if (!err) {
                    res.status(200).json(res);
                } else {
                    res.status(404).json(err);
                }
            });


        var tweetsIdCallback = function (err, tweetsIds) {

        };

        var tweetsContentCallback = function (err, tweets) {
            if (!err) {
                res.status(200).json(tweets);
            } else {
                res.status(404).json(err);
            }
        }
    });
};
