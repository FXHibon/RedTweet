package fr.epsi.tp.redtweet.controllers;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.service.RedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by fhibon on 01/04/2015.
 * Simply expose the TweetService as a REST api
 */
@Controller
public class RestController {

    @Resource
    private RedService redService;

    @RequestMapping(value = "/user_timeline", method = RequestMethod.GET)
    public List<Tweet> getUserTimeLine(@RequestBody Map<String, String> ref) {
        return redService.getUserTimeLine(new User(ref));
    }

    @RequestMapping(value = "/home_timeline", method = RequestMethod.GET)
    public List<Tweet> getHomeTimeLine(HttpServletRequest request) {
        return redService.getHomeTimeLine((User) request.getSession().getAttribute("user"));
    }

    @RequestMapping(value = "/retweets_of_me", method = RequestMethod.GET)
    public List<Tweet> getRetweetsOfMe(HttpServletRequest request) {
        return redService.getRetweetsOfMe((User) request.getSession().getAttribute("user"));
    }

    @RequestMapping(value = "/retweets/{userName}", method = RequestMethod.GET)
    public List<Tweet> getRetweets(@PathVariable String userName) {
        return redService.getRetweets(userName);
    }

    @RequestMapping(value = "/destroy/{tweetId}", method = RequestMethod.POST)
    public Map<String, Object> destroy(@PathVariable String tweetId, HttpServletRequest request) {
        return redService.destroy((User) request.getSession().getAttribute("user"), tweetId);
    }

    @RequestMapping(value = "/retweet/{tweetid}", method = RequestMethod.POST)
    public Map<String, Object> retweet(@PathVariable String tweetid) {
        return null;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> update(@RequestBody Map<String, String> tweet, HttpServletRequest request) {
        return redService.update(new Tweet(tweet), (User) request.getSession().getAttribute("user"));
    }

    @RequestMapping(value = "/auht", method = RequestMethod.GET)
    public Map<String, Object> auth(@RequestBody Map<String, String> user) {
        return redService.auth(new User(user));
    }
}
