package ru.ulstu.is.sbapp.student.controller;
import ru.ulstu.is.sbapp.student.model.Position;
import ru.ulstu.is.sbapp.student.model.Worker;

public class DopDepartmentDto {

    private String position;
    private double avgSalary;

    public DopDepartmentDto() { }
    public DopDepartmentDto(String position, double avgSalary) {
        this.position = position;
        this.avgSalary = avgSalary;
    }

    public String getPosition() { return position; }

    public double getAvgSalary() { return avgSalary; }

    public void setPosition(String position) { this.position = position; }

    public void setAvgSalary(double  avgSalary) { this.avgSalary = avgSalary; }

}
