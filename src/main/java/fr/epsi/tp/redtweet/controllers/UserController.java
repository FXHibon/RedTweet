package fr.epsi.tp.redtweet.controllers;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by fx on 23/03/2015.
 */
@Controller
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "user")
    public User getUser() {
        return userService.getUser();
    }

}
