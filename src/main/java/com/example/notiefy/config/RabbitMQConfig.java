package com.example.notiefy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
    @Value("${rabbitmq.song-added.queue}")
    private String songAddedQueueName;
    @Value("${rabbitmq.song-played.queue}")
    private String songPlayedQueueName;
    @Value("${rabbitmq.song-added.exchange}")
    private String songAddedExchangeName;
    @Value("${rabbitmq.song-played.exchange}")
    private String songPlayedExchangeName;
    @Value("${rabbitmq.song-added.key}")
    private String songAddedKey;
    @Value("${rabbitmq.song-played.key}")
    private String songPlayedKey;

    @Bean
    public Queue songAddedQueue() {
        return new Queue(songAddedQueueName, false);
    }

    @Bean
    public Queue songPlayedQueue() {
        return new Queue(songPlayedQueueName, false);
    }

    @Bean
    public Exchange songAddedExchange() {
        return new TopicExchange(songAddedExchangeName, false, false);
    }

    @Bean
    public Exchange songPlayedExchange() {
        return new TopicExchange(songPlayedExchangeName, false, false);
    }

    @Bean
    public Binding songAddedBinding(Queue songAddedQueue, Exchange songAddedExchange) {
        return BindingBuilder.bind(songAddedQueue)
                .to(songAddedExchange)
                .with(songAddedKey)
                .noargs();
    }

    @Bean
    public Binding songPlayedBinding(Queue songPlayedQueue, Exchange songPlayedExchange) {
        return BindingBuilder.bind(songPlayedQueue)
                .to(songPlayedExchange)
                .with(songPlayedKey)
                .noargs();
    }
}

