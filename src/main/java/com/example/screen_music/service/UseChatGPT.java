package com.example.screen_music.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class UseChatGPT {

    private final ChatClient chatClient;

    public UseChatGPT(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getInfo(String text){
        return chatClient.prompt()
                .options(OpenAiChatOptions.builder()
                        .withMaxTokens(150)
                        .withTemperature(0.4)
                        .build())
                .user("Give a short summary (max 100 words) about the artist " + text + ", focus on genre, origin, and most famous songs.")
                .call()
                .content();
    }
}
