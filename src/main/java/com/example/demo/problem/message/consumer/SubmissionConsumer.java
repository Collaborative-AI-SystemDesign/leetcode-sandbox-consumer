package com.example.demo.problem.message.consumer;

import com.example.demo.problem.SubmissionProcessor;
import com.example.demo.problem.message.dto.SubmissionRequest;
import com.example.demo.problem.message.dto.SubmissionResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubmissionConsumer {

    private final RabbitTemplate rabbitTemplate;
    private final SubmissionProcessor processor;

    @Value("${rabbitmq.submission.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.submission.result.routing.key}")
    private String resultRoutingKey;

    @Async("mqExcutor")
    @RabbitListener(
            queues = "${rabbitmq.submission.request.queue.name}",
            ackMode = "NONE"
    )
    public void handleSubmissionRequest(SubmissionRequest request) {
        SubmissionResult executionResult = processor.processSubmission(request);
        log.info("Sending processing result to result queue: {}", executionResult);

        rabbitTemplate.convertAndSend(exchangeName, resultRoutingKey, executionResult);
    }
}
