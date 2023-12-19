package ru.ulstu.is.sbapp.student.service;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(Long id) {
        super(String.format("Position with id [%s] is not found", id));
    }
}

