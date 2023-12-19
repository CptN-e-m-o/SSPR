package ru.ulstu.is.sbapp.student.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "add_fkd", nullable = true)
    private Department department;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "add_fkp", nullable = true)
    private Position position;

    public Worker() {}

    public Worker(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) { this.department = department; }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) { this.position = position; }
    //public void setPositions (List<Position> positions){this.positions = positions;}

    /*public void addPosition(Position position) {
        positions.add(position);
        if (!position.getWorkers().contains(this)) {
            position.addWorker(this);
        }
    }

    public List<Position> getPositions(){return this.positions;}*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
