package fr.epsi.tp.redtweet.controllers;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by fx on 23/03/2015.
 */
@Controller
public class UserController {

    public static final String USER = "user";

    @Resource
    private UserService userService;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Map> auth(@RequestParam Map<String, String> user, HttpServletRequest servletRequest) {

        User userBean = new User(user);
        if (userService.auth(userBean)) {
            servletRequest.getSession(true)
                    .setAttribute(USER, userBean);

            return new ResponseEntity<Map>(userBean, HttpStatus.OK);
        } else {
            return new ResponseEntity<Map>(userBean, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<User> refresh(HttpServletRequest request) {
        User user = (User) request.getSession(true).getAttribute(USER);
        if (user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity logout(HttpServletRequest request) {
        try {
            request.getSession(true).removeAttribute(USER);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
