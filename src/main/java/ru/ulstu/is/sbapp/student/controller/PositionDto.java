package ru.ulstu.is.sbapp.student.controller;
import ru.ulstu.is.sbapp.student.model.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PositionDto {
    private long id;
    private String name;
    private List<Long> workers;

    public PositionDto(Position position) {
        this.id = position.getId();
        this.name = position.getName();
        workers = new ArrayList<>();
        if (position.getWorkers() != null) {
            for (var worker : position.getWorkers()) {
                workers.add(worker.getId());
            }
        }

    }

    public PositionDto() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Long> getWorkers() { return workers; }
}
