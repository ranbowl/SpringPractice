package com.springpractice.api.serviceimpl;

import com.springpractice.api.config.RabbitQueueConfig;
import com.springpractice.api.service.QueueService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class QueueServiceImpl implements QueueService {

    private final RabbitTemplate testRabbitTemplate;

    private static final Logger logger = LogManager.getLogger(QueueServiceImpl.class);

    @Autowired
    public QueueServiceImpl(RabbitTemplate testRabbitTemplate) {
        this.testRabbitTemplate = testRabbitTemplate;
    }

    @PostConstruct
    public void setTestRabbitTemplate() {
        testRabbitTemplate.setMandatory(true);
        testRabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause)  -> {
            if (!ack) {
                logger.error("Test: failed to deliver to test exchange. Message -> {}, Cause -> {}", correlationData.getId(), cause);
            } else {
                logger.info("Test: delivered to exchange. Message -> {}", correlationData.getId());
            }
        });
        testRabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText, String exchange, String routingKey) -> {
            logger.error("Test: failed to deliver to test queue. Message -> {}, Reply code -> {}, Reply text -> {}, Exchange -> {}, Routing key -> {}",
                    message.getBody(), replyCode, replyText,exchange, routingKey);
        });
    }

    @Override
    public void pushTest(String message) {
        CorrelationData correlationData = new CorrelationData(message);
        testRabbitTemplate.convertAndSend(RabbitQueueConfig.TEST_EXCHANGE, RabbitQueueConfig.TEST_ROUTING_KEY, message, correlationData);
    }
}
