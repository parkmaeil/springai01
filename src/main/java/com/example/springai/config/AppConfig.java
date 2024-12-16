package com.example.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class AppConfig {

    @Value("classpath:/prompt.txt")
    private Resource resource;

    // ChatClient
  /*  @Bean
    public ChatClient chatBuilder(ChatClient.Builder builder){
        return builder.defaultSystem(resource).build();
    }*/
    @Bean
    public ChatClient chatBuilder(ChatClient.Builder builder){
        return builder.defaultAdvisors(
                new MessageChatMemoryAdvisor(new InMemoryChatMemory())).build();
    }
}
