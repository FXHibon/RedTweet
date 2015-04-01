package fr.epsi.tp.redtweet.controllers;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.service.TweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fhibon on 01/04/2015.
 */
@Controller
public class TweetController {

    @Resource
    private TweetService tweetService;

    @RequestMapping(value = "/home_timeline", method = RequestMethod.GET)
    public ResponseEntity<List<Tweet>> getTweets(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        ResponseEntity<List<Tweet>> resp =
                new ResponseEntity<List<Tweet>>(
                        tweetService.getUserTimeLine(user),
                        HttpStatus.OK
                );
        return resp;
    }


}
