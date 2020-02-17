package com.example.demo.controller;

import com.example.demo.server.WebSocketServer;
import com.example.demo.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WebSocketController {
    @Autowired
    WebSocketServer webSocketServer;
    @PostMapping("/login")
    public String login(String username,String password) throws IOException {
        //TODO: 校验密码
        webSocketServer.sendInfo(username + "进入了聊天室!");
        return username;
    }


}
