package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.dao.UserMapper;
import com.example.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller

public class LoginController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       Map<String,Object>map, HttpSession session){
        User user = userMapper.getByup(username, password);
        session.setAttribute("username",username);
        if (user != null){
            stringRedisTemplate.opsForValue().append("username",username);
            return "redirect:/main.html";
        }else {
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/liaotian")
    public String liaotian(){
        return "liaotian";
    }

    @RequestMapping("/index")
    public String liaotian2(){
        return "index";
    }

}
