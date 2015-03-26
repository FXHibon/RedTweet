package fr.epsi.tp.redtweet.controllers;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by fx on 23/03/2015.
 */
@Controller
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<User> auth(@RequestBody User user, HttpServletRequest servletRequest) {
        if (userService.auth(user)) {
            servletRequest.getSession(true)
                    .setAttribute("user", user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        }
    }

}
