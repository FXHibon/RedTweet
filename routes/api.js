var express = require('express');
var router = express.Router();

router.use(function (req, res, next) {
    console.log("before call an api");
    next();
});

/*******************
 *  API routes
 *******************/
router.get('/auth', function (req, res, next) {
    res.json({data: "coucou"});
});


/*******************
 * Default route when no matching route found
 *******************/
router.use(function (req, res, next) {
    res.status(400).json({reason: "api's method not found", url: req.baseUrl, method: req.method, params: req.query});
});

module.exports = router;