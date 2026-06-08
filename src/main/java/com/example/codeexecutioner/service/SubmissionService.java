package com.example.codeexecutioner.service;

import com.example.codeexecutioner.dto.SubmissionRequest;
import com.example.codeexecutioner.model.Submission;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Service class for handling code submissions.
 * Executes submitted Python code using ProcessBuilder and captures the output.
 */
@Service
public class SubmissionService {

    private final Map<String, Submission> submissionMap = new HashMap<>();

    /**
     * Handles a code submission request.
     * Saves the source code to a temp file, executes it with Python, and captures the output.
     *
     * @param request The submission request containing problemId and sourceCode.
     * @return The Submission object with execution results.
     */
    public Submission handleSubmission(SubmissionRequest request) {
        Submission submission = new Submission(request.getProblemId(), request.getSourceCode());
        submissionMap.put(submission.getSubmissionId(), submission);

        try {
            String output = executePython(request.getSourceCode());
            submission.setOutput(output);
            submission.setStatus("COMPLETED");
        } catch (Exception e) {
            submission.setOutput("Error during execution: " + e.getMessage());
            submission.setStatus("FAILED");
        }

        return submission;
    }

    /**
     * Executes Python source code by writing it to a temp .py file and running it.
     */
    private String executePython(String sourceCode) throws Exception {
        // Create a temporary .py file
        Path tempFile = Files.createTempFile("code_", ".py");
        Files.writeString(tempFile, sourceCode);

        try {
            ProcessBuilder pb = new ProcessBuilder("python", tempFile.toString());
            pb.redirectErrorStream(true); // Merge stderr into stdout
            Process process = pb.start();

            // Read the output
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            // Wait up to 10 seconds for the process to finish
            boolean finished = process.waitFor(10, TimeUnit.SECONDS);
            if (!finished) {
                process.destroyForcibly();
                return "Error: Code execution timed out (10 second limit).";
            }

            return output.toString().trim();

        } finally {
            // Clean up the temp file
            Files.deleteIfExists(tempFile);
        }
    }

    /**
     * Retrieves a submission by its ID.
     *
     * @param submissionId The ID of the submission.
     * @return The Submission object, or null if not found.
     */
    public Submission getSubmission(String submissionId) {
        return submissionMap.get(submissionId);
    }
}