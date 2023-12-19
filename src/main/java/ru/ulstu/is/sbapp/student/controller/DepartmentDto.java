package ru.ulstu.is.sbapp.student.controller;
import ru.ulstu.is.sbapp.student.model.Department;
import java.util.List;
import ru.ulstu.is.sbapp.student.model.Worker;
import java.util.ArrayList;
import java.util.Objects;


public class DepartmentDto {
    private long id;
    private String city;
    private String street;
    private String house;
    private Integer apartment;
    private List<Long> workers;
    private double avgSalary;

    public DepartmentDto() {
    }

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.city = department.getCity();
        this.street = department.getStreet();
        this.house = department.getHouse();
        this.apartment = department.getApartment();
        workers = new ArrayList<>();
        if (department.getWorkers() != null) {
            for (var worker : department.getWorkers()) {
                workers.add(worker.getId());
            }
        }
        this.avgSalary = department.getAvgSalary();
    }

    public long getId() {
        return id;
    }
    public String getCity() {
        return city;
    }
    public String getStreet() {
        return street;
    }
    public String getHouse() {
        return house;
    }
    public Integer getApartment() {
        return apartment;
    }
    public double getAvgSalary() {
        return avgSalary;
    }
    public List<Long> getWorkers() { return workers; }

}
