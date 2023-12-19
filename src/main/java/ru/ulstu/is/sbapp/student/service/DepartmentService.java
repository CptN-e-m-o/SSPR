package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.student.controller.DopDepartmentDto;
import ru.ulstu.is.sbapp.student.model.Department;
import ru.ulstu.is.sbapp.student.controller.DepartmentDto;
import ru.ulstu.is.sbapp.student.repository.DepartmentRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ValidatorUtil validatorUtil;

    public DepartmentService(DepartmentRepository departmentRepository,
                          ValidatorUtil validatorUtil) {
        this.departmentRepository = departmentRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Department addDepartment(String city, String street, String house, Integer apartment, double avgsalary) {
        final Department department = new Department(city, street, house, apartment, avgsalary);
        validatorUtil.validate(department);
        return departmentRepository.save(department);

    }

    @Transactional(readOnly = true)
    public Department findDepartment(Long id) {
        final Optional<Department> department = departmentRepository.findById(id);
        return department.orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional
    public Department updateDepartment(Long id, String city, String street, String house, Integer apartment, double avgsalary) {
        final Department currentDepartment = findDepartment(id);
        currentDepartment.setCity(city);
        currentDepartment.setStreet(street);
        currentDepartment.setHouse(house);
        currentDepartment.setApartment(apartment);
        currentDepartment.setAvgSalary(avgsalary);
        validatorUtil.validate(currentDepartment);
        return departmentRepository.save(currentDepartment);
    }

    /*public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        return new DepartmentDto(updateDepartment(departmentDto.getId(), departmentDto.get(),
                departmentDto.пу(), learningPlans, teacher));

    }*/

    @Transactional
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        return new DepartmentDto(updateDepartment(departmentDto.getId(), departmentDto.getCity(), departmentDto.getStreet(), departmentDto.getHouse(), departmentDto.getApartment(), departmentDto.getAvgSalary()));
    }


   /* public Department updateDepartment(Long id, String city, String street, String house, int apartment ) {
        final Department currentDepartment = findDepartment(id);
        currentDepartment.setCity(city);
        currentDepartment.setStreet(street);
        currentDepartment.setHouse(house);
        currentDepartment.setApartment(apartment);
        validatorUtil.validate(currentDepartment);
        return departmentRepository.save(currentDepartment);
    }*/


    @Transactional
    public Department deleteDepartment(Long id) {
        final Department currentDepartment = findDepartment(id);
        departmentRepository.delete(currentDepartment);
        return currentDepartment;
    }

    @Transactional
    public void deleteAllDepartments() {
        departmentRepository.deleteAll();
    }

   /* @Transactional
    public List<DopDepartmentDto> avgSalary(Long depid) {
        return departmentRepository.avgSalary(depid);
    }*/

}
