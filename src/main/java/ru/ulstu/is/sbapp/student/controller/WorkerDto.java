package ru.ulstu.is.sbapp.student.controller;
import ru.ulstu.is.sbapp.student.model.Worker;

public class WorkerDto {
    private long id;
    private String fullname;
    private String email;
    private long department;
    private long position;

    public WorkerDto() { }

    public WorkerDto(Worker worker) {
        this.id = worker.getId();
        this.fullname = worker.getFullName();
        this.email = worker.getEmail();
        if (worker.getDepartment() != null) {
            department = worker.getDepartment().getId();
        }
        if (worker.getPosition() != null) {
            position = worker.getPosition().getId();
        }

    }


    public long getId() {
        return id;
    }

    public String getName() {
        return fullname;
    }
    public String getEmail() {
        return email;
    }
    public long getDepartment(){ return department; }

    public long getPosition() { return position; }


}
