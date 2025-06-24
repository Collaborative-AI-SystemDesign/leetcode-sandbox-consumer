package com.example.demo.problem.message.dto;

import com.example.demo.submission.SubmissionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubmissionResult {
    private Long submissionId;
    private Long contestId;
    private SubmissionStatus status;
}
