package com.springpractice.api.serviceimpl;

import com.springpractice.api.config.RabbitQueueConfig;
import com.springpractice.api.service.MqListnerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MqListnerServiceImpl implements MqListnerService {

    private static final Logger logger = LogManager.getLogger(MqListnerServiceImpl.class);

    @RabbitListener(queues = RabbitQueueConfig.TEST_QUEUE, concurrency = "2")
    public void listner(String message) throws IOException {
        logger.info("Received message -> {} from test queue.", message);
    }
}
