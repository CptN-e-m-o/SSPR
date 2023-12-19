package ru.ulstu.is.sbapp.student.service;

public class WorkerNotFoundException extends RuntimeException {
    public WorkerNotFoundException(Long id) {
        super(String.format("Worker with id [%s] is not found", id));
    }
}

