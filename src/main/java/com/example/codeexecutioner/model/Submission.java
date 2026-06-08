package com.example.codeexecutioner.model;

import java.util.UUID;

/**
 * Model class for a code submission.
 */
public class Submission {

    private String submissionId;
    private String problemId;
    private String sourceCode;
    private String status;
    private String output;

    public Submission(String problemId, String sourceCode) {
        this.submissionId = UUID.randomUUID().toString();
        this.problemId = problemId;
        this.sourceCode = sourceCode;
        this.status = "PENDING";
        this.output = "";
    }

    // Getters and setters

    public String getSubmissionId() {
        return submissionId;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}