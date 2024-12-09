package com.example.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService { // new ChatService()

    // LLM(gtp)와 통신을 하기 위한 API 설정
    private final ChatClient chatClient;
    // ChatClient 객체를 생성------API key--------->LLM과 통신
    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    // LLM에 메세지(Prompt)를 보낸다
    public String chatMessage(String message){
        //  프롬프트엔지니어링
       return chatClient.prompt()
                .user(message)
                .call() // 요청
                .content(); // 응답을 문자열로 받는다.
    }
}
