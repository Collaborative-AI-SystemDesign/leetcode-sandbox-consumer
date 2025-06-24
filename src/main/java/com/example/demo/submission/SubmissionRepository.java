package com.example.demo.submission;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubmissionRepository {

    private final JdbcTemplate jdbcTemplate;

    public int updateSubmissionStatus(Long submissionId, SubmissionStatus status) {
        String sql = """
                UPDATE submission 
                SET status = ? 
                WHERE id = ?
                """
                ;

        return jdbcTemplate.update(sql, status.name(), submissionId);
    }

}
