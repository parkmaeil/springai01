package com.example.springai.service;

import com.example.springai.entity.Answer;
import com.example.springai.entity.Movie;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class ChatService { // new ChatService()


    private final ChatClient chatClient;
    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    // LLM에 메세지(Prompt)를 보낸다  ?message=뉴턴의 운동 제2법칙을 간단하게 설명하세요
    public String chatMessage(String subject, String tone, String message){
        //  프롬프트엔지니어링
       return chatClient.prompt()
                .user(message)  // User Message
                .system(sp->sp
                        .param("subject", subject)
                        .param("tone", tone)
                )
                .call() // 요청
               .chatResponse()
               .getResult()
               .getOutput()
               .getContent();
             //   .content(); // 응답을 (문자열)로 받는다.
    }

    public Answer getRecipe(String question) {
         return chatClient.prompt()
                 .user(question)
                 .call()
                 .entity(Answer.class);
    }
    // 프롬프트엔지니어링(중요)
    private final String recipeTemplate= """
             Answer for {foodName} for {question}?
            """;
    public Answer getRecipe2(String foodName, String question) {
         return  chatClient.prompt()
                 .user(userSpec->userSpec.text(recipeTemplate)
                         .param("foodName", foodName) // 버거
                         .param("question", question) // 주요 재료는 무엇인가요? , 요리 순서를 알려주세요.
                 )
                 .call()
                 .entity(Answer.class);
    }

    public List<String> chatList(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));
    }

    public Map<String, String> chatMap(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<Map<String, String>>() {});
    }

    public List<Movie> chatMovie(String directorName) {
        String template= """
             "Generate a list of movies directed by {directorName}. If the director is unknown, return null.
            한국 영화는 한글로 표기해줘.
             Each movie should include a title and release year. {format}"
             """;
            List<Movie> movieList=chatClient.prompt()
                    .user(userSpec->userSpec.text(template)
                            .param("directorName", directorName)
                            .param("format", "json")
                    )
                    .call()
                    .entity(new ParameterizedTypeReference<List<Movie>>() {});

           return movieList;
    }

    public String getResponse(String message){
        return  chatClient.prompt().user(message).call().content();
    }
    public void startChat(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your message: ");
        while(true){
            String message=scanner.nextLine();
            if(message.equals("exit")){
                System.out.println("Exiting chat...");
                break;
            }
            String response=getResponse(message);
            System.out.println("Bot(LLM): "+ response);
        }
        scanner.close();
    }


}
