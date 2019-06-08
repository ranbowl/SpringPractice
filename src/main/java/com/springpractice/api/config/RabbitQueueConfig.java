package com.springpractice.api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

import java.util.HashMap;
import java.util.Map;

@EnableRabbit
@Configuration
public class RabbitQueueConfig implements RabbitListenerConfigurer {
    public static final String TEST_QUEUE = "test-queue";
    public static final String TEST_EXCHANGE = "test-exchange";
    public static final String TEST_ROUTING_KEY = "test-routing-key";

    public static final String DEAD_TEST_QUEUE = "dead-test-queue";
    public static final String DEAD_TEST_EXCHANGE = "dead-test-exchange";
    public static final String DEAD_TEST_ROUTING_KEY = "dead-test-routing-key";

    public static final String DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    @Bean
    public Queue testQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put(DEAD_LETTER_EXCHANGE, DEAD_TEST_EXCHANGE);
        args.put(DEAD_LETTER_ROUTING_KEY, DEAD_TEST_ROUTING_KEY);
        return new Queue(TEST_QUEUE, true, false, false, args);
    }

    @Bean
    public DirectExchange testExchange() {
        return new DirectExchange(TEST_EXCHANGE);
    }

    @Bean
    public Binding testBinding(Queue testQueue, DirectExchange testExchange) {
        return BindingBuilder.bind(testQueue).to(testExchange).with(TEST_ROUTING_KEY);
    }

    @Bean
    public Queue deadTestQueue() {
        return new Queue(DEAD_TEST_QUEUE, true);
    }

    @Bean
    public DirectExchange deadTestExchange() {
        return new DirectExchange(DEAD_TEST_EXCHANGE);
    }

    @Bean
    public Binding deadTestBinding(Queue deadTestQueue, DirectExchange deadTestExchange) {
        return BindingBuilder.bind(deadTestExchange).to(deadTestExchange).with(DEAD_TEST_ROUTING_KEY);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        return new DefaultMessageHandlerMethodFactory();
    }
}
