package com.example.codeexecutioner.controller;

import com.example.codeexecutioner.dto.SubmissionRequest;
import com.example.codeexecutioner.model.Submission;
import com.example.codeexecutioner.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling code submissions.
 */
@RestController
@RequestMapping("/api")
public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    /**
     * Endpoint to handle code submissions.
     * Executes the code synchronously and returns the result with output.
     *
     * @param request The submission request containing problemId, sourceCode, and language.
     * @return ResponseEntity with submission details including execution output.
     */
    @PostMapping("/submit")
    public ResponseEntity<Submission> submitCode(@RequestBody SubmissionRequest request) {
        Submission submission = submissionService.handleSubmission(request);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve a past submission by its ID.
     *
     * @param id The submission ID.
     * @return The submission if found, or 404.
     */
    @GetMapping("/submission/{id}")
    public ResponseEntity<Submission> getSubmission(@PathVariable String id) {
        Submission submission = submissionService.getSubmission(id);
        if (submission == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }
}