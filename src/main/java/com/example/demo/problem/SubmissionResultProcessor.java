package com.example.demo.problem;

import com.example.demo.submission.SubmissionRepository;
import com.example.demo.submission.SubmissionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionResultProcessor {

    private final SubmissionRepository submissionRepository;

    public void updateSubmissionResult(Long submissionId, SubmissionStatus resultStatus) {
        int queryResult = submissionRepository.updateSubmissionStatus(submissionId, resultStatus);

        if (queryResult == 0) {
            throw new IllegalArgumentException("Submission not found: " + submissionId);
        }
    }
}
