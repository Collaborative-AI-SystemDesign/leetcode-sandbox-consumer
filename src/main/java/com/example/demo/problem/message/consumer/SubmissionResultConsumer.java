package com.example.demo.problem.message.consumer;

import com.example.demo.problem.SubmissionResultProcessor;
import com.example.demo.problem.message.dto.SubmissionResult;
import com.example.demo.submission.SubmissionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubmissionResultConsumer {

    private final SubmissionResultProcessor processor;

    @RabbitListener(
            queues = "${rabbitmq.submission.result.queue.name}",
            ackMode = "NONE"
    )
    public void handleSubmissionResult(SubmissionResult result) {
        Long submissionId = result.getSubmissionId();
        SubmissionStatus resultStatus = result.getStatus();
        processor.updateSubmissionResult(submissionId, resultStatus);

        log.info("complete processing submission result: {}", submissionId);
    }
}
