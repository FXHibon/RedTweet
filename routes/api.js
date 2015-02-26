var express = require('express');
var router = express.Router();
var debug = require('debug')('RedTweet:api');

router.use(function (req, res, next) {
    debug("API called: ", {
        url: req.originalUrl,
        method: req.method,
        params: req.query,
        body: req.body
    });
    next();
});

/**
 * Add API routes
 */
require('../api/api.js')(router);

/*********************************************
 * Default route when no matching route found
 *********************************************/
router.use(function (req, res, next) {
    res.status(400).json({
        cause: "api's method not found",
        url: req.originalUrl,
        method: req.method,
        params: req.query,
        body: req.body
    });
});

module.exports = router;