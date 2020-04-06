package com.hdm.restdemo.controller;

import com.hdm.restdemo.pojo.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: HelloController
 * @description:
 * @author: huangdaming
 * @Date: 2020-04-06 17:37
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public User hello(@RequestParam("id") String id) {
        User user = new User();
        user.setId(8L);
        user.setAge(21);
        user.setName("柳岩");
        user.setUserName("liuyan");
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/AuthTest", method = RequestMethod.GET)
    public String AuthTest(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        return "OK";
    }
}
