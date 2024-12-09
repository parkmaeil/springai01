package com.example.springai.controller;

import com.example.springai.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired // DI
    private ChatService chatService;

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message){
        return chatService.chatMessage(message);
    }//
}
