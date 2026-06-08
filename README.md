# Online Code Executor

A web-based Python code execution tool built with Spring Boot. You type Python code in the browser, hit Run, and the server executes it on the machine and sends the output back to you. That is the whole idea.

It was built as a personal project to understand how online judges and code playgrounds work under the hood — specifically how a backend safely spawns a process, captures its output, and returns it to a frontend in real time.


## How it works

The frontend is a single HTML page served by Spring Boot. It has a split-panel layout: code editor on the left, output on the right. When you click Run, the page sends the code as a POST request to `/api/submit`.

On the backend, the `SubmissionService` takes that code, writes it to a temporary `.py` file on disk, and runs it using `ProcessBuilder` (which spawns an actual `python` process). It reads whatever the process prints to stdout and stderr, waits up to 10 seconds for it to finish, then returns the output in the response. The temp file is deleted afterwards.

The frontend picks up the response and displays the output in the right panel, along with a status badge showing whether the run succeeded or failed.


## Screenshots

<!-- Add a screenshot of the main interface here -->

<!-- Add a screenshot showing a code execution result here -->


## Requirements

You need Java 17, Maven, and Python 3 installed on the machine running the server. Python must be accessible as `python` on the system PATH, since that is the command the backend calls.


## Running it locally

Clone the repo, then from the project root run:

```
mvn spring-boot:run
```

Once it starts, open your browser and go to `http://localhost:8080`. The interface loads immediately, no extra setup needed.


## API

The backend exposes two endpoints.

**POST /api/submit**

Accepts a JSON body with `problemId` (just a label, can be anything) and `sourceCode` (the Python code to run). Returns a submission object with the execution output and a status of either `COMPLETED` or `FAILED`.

```json
{
  "problemId": "playground",
  "sourceCode": "print('hello world')"
}
```

**GET /api/submission/{id}**

Retrieves a past submission by its ID. The submissions are stored in memory, so they are lost when the server restarts.


## Stack

Spring Boot 3.1 handles the backend and serves the static frontend. There is no database. The frontend is plain HTML, CSS, and JavaScript with no frameworks. Code execution happens through Java's `ProcessBuilder` calling the system Python interpreter directly.