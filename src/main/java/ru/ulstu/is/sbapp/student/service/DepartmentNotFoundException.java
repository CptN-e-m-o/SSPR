package ru.ulstu.is.sbapp.student.service;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Long id) {
        super(String.format("Department with id [%s] is not found", id));
    }
}
