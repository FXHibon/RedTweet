package fr.epsi.tp.redtweet.controllers;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.service.RedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fhibon on 01/04/2015.
 * Simply expose the TweetService as a REST api
 */
@Controller
@ResponseBody
public class RestController {

    @Resource
    private RedService redService;

    @RequestMapping(value = "/home_timeline", method = RequestMethod.POST)
    public Map<String, String> tweet(@RequestBody Map<String, String> tweetMap, HttpServletRequest request) {
        return redService.tweet((User) request.getSession().getAttribute("user"), new Tweet(tweetMap));
    }

    @RequestMapping(value = "/user_timeline", method = RequestMethod.GET)
    public Map getUserTimeLine(@RequestParam String userName) {
        User user = new User()
                .setUsername(userName);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("tweets", redService.getUserTimeLine(user));
        result.putAll(redService.search(user.getUsername()));
        return result;
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

    @RequestMapping(value = "/retweet/{id}", method = RequestMethod.POST)
    public Map<String, Object> retweet(@PathVariable String id, HttpServletRequest request) {
        return redService.retweet((User) request.getSession().getAttribute("user"), id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> update(@RequestBody Map<String, String> tweet, HttpServletRequest request) {
        return redService.update(new Tweet(tweet), (User) request.getSession().getAttribute("user"));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Map search(@RequestParam String query) {
        return redService.search(query);
    }
}
