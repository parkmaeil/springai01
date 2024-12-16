package com.example.springai.controller;

import com.example.springai.entity.Answer;
import com.example.springai.entity.Movie;
import com.example.springai.service.ChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ChatController {

    @Autowired // DI
    private ChatService chatService;

    @GetMapping("/chat")
    public String chat(String subject, String tone, String message){
        return chatService.chatMessage(subject, tone, message);
    }//

    @GetMapping("/recipe")
    // { "answer" : "sasasasasasasasa" }
    public Answer recipe(String question){
         return chatService.getRecipe(question);
    }
    @GetMapping("/recipe2")
    public Answer recipe2(String foodName, String question){
        return chatService.getRecipe2(foodName, question);
    }

    @GetMapping("/chatList")
    public List<String> chatList(String message){
          return chatService.chatList(message);
    }

    @GetMapping("/chatmap")
    public Map<String, String> chatMap(String message){
         return  chatService.chatMap(message);
    }

    @GetMapping("/chatmovie")
    public List<Movie> chatMovie(String  directorName){
          return chatService.chatMovie(directorName);
    }
}
