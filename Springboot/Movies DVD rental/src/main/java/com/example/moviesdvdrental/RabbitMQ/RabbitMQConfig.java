package com.example.moviesdvdrental.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String RENTAL_EXCHANGE = "rentalExchange";
    public static final String RENTAL_QUEUE = "rentalQueue";
    public static final String STOCKS_QUEUE = "stocksQueue";


    @Bean
    public Queue rentalQueue() {
        return new Queue(RENTAL_QUEUE, true);
    }

    @Bean
    Queue stocksQueue(){
        return new Queue(STOCKS_QUEUE, true);
    }


    // Exchange declaration using ExchangeBuilder
    @Bean
    public DirectExchange rentalExchange() {
        return ExchangeBuilder.directExchange(RENTAL_EXCHANGE)
                .durable(true)
                .build();
    }


    //    @Bean
//    public Binding rentalBinding(Queue orderQueue, DirectExchange orderExchange) {
//        return BindingBuilder.bind(orderQueue).to(orderExchange).with("rental");
    @Bean
    Binding rentalBinding(){
        return  new Binding(RENTAL_QUEUE,Binding.DestinationType.QUEUE, RENTAL_EXCHANGE,"rental",null);
    }

    @Bean
    Binding stocksBinding(){
        return BindingBuilder
                .bind(stocksQueue())
                .to(rentalExchange())
                .with("stocks");
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter()); // Set SimpleMessageConverter for serialization
        return rabbitTemplate;
    }

    //    @Bean
//    ConnectionFactory connectionFactory(){
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
//        cachingConnectionFactory.setUsername("guest");
//        cachingConnectionFactory.setPassword("guest");
//        return cachingConnectionFactory;
//    }
//    @Bean
//    MessageListenerContainer messageListenerContainer(){
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(connectionFactory()
//        );
//        simpleMessageListenerContainer.setQueues(myQueue());
//        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
//        return simpleMessageListenerContainer;
//    }
}
