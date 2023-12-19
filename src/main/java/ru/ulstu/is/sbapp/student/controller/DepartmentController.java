package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.student.service.DepartmentService;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public DepartmentDto getDepartment(@PathVariable Long id) {
        return new DepartmentDto(departmentService.findDepartment(id));
    }

    @GetMapping("/")
    public List<DepartmentDto> getDepartments() {
        return departmentService.findAllDepartments().stream()
                .map(DepartmentDto::new)
                .toList();
    }

    /*@PostMapping("/")
    public DepartmentDto createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        return departmentService.addDepartment(departmentDto);
    }*/
    @PostMapping("/")
    public DepartmentDto createDepartment(@RequestParam("city") String city, @RequestParam("street") String street, @RequestParam("house") String house, @RequestParam("apartment") int apartment, @RequestParam("avgsalary") double avgsalary) {
        return new DepartmentDto(departmentService.addDepartment(city, street, house, apartment, avgsalary));
    }

    @PatchMapping("/{id}")
    public DepartmentDto updateDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        return departmentService.updateDepartment(departmentDto);
    }


    @DeleteMapping("/{id}")
    public DepartmentDto deleteDepartment(@PathVariable Long id) {
        return new DepartmentDto(departmentService.deleteDepartment(id));
    }

   /* @GetMapping("/query")
    public List<DopDepartmentDto> getAvgSalary(@RequestParam Long depid) {
        return departmentService.avgSalary(depid);
    }*/

}
