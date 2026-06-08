package com.example.codeexecutioner.dto;

/**
 * DTO for submission requests.
 */
public class SubmissionRequest {

    private String problemId;
    private String sourceCode;
    private String language; // "java" or "python"

    // Getters and setters

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}