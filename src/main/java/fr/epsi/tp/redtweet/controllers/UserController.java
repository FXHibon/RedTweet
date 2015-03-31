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
import java.util.Map;

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
    ResponseEntity<Map> auth(@RequestBody Map<String, String> user, HttpServletRequest servletRequest) {
        User userBean = new User(user);
        if (userService.auth(new User(user))) {
            servletRequest.getSession(true)
                    .setAttribute("user", user);
            return new ResponseEntity<Map>(userBean.getMap(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Map>(userBean.getMap(), HttpStatus.BAD_REQUEST);
        }
    }
}
