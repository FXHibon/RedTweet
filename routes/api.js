var express = require('express');
var router = express.Router();

/**
 * Add API routes
 */
require('../api/api.js')(router);

/*********************************************
 * Default route when no matching route found
 *********************************************/
router.use(function (req, res, next) {
    res.status(400).json({
        reason: "api's method not found",
        url: req.originalUrl,
        method: req.method,
        params: req.query
    });
});

module.exports = router;