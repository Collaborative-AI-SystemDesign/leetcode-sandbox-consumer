package com.example.demo.problem;

import static com.example.demo.submission.SubmissionStatus.PENDING;
import static com.example.demo.submission.SubmissionStatus.SUCCESS;

import com.example.demo.problem.message.dto.SubmissionRequest;
import com.example.demo.problem.message.dto.SubmissionResult;
import org.springframework.stereotype.Service;

@Service
public class SubmissionProcessor {

    public SubmissionResult processSubmission(SubmissionRequest request) {
        Long submissionId = request.getSubmissionId();
        Long contestId = request.getContestId();
        try {
            executeJavaCode(request);
            return new SubmissionResult(
                    submissionId,
                    contestId,
                    SUCCESS
            );
        } catch (Exception e) {
            return new SubmissionResult(
                    submissionId,
                    contestId,
                    PENDING
            );
        }
    }

    private void executeJavaCode(SubmissionRequest request) throws InterruptedException {
        // 실제로는 Docker 컨테이너나 샌드박스 환경에서 실행
        // 시뮬레이션: 실제 코드 실행
        Thread.sleep(2000);
    }
}
