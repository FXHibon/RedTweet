module.exports = function (router) {

    /*******************
     *  API routes
     *******************/

    /**
     * handle authentication
     */
    router.get('/auth', function (req, res, next) {
        res.json({data: "coucou"});
    });

};