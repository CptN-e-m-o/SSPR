package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.student.controller.WorkerDto;
import ru.ulstu.is.sbapp.student.model.Department;
import ru.ulstu.is.sbapp.student.model.Position;
import ru.ulstu.is.sbapp.student.model.Worker;
import ru.ulstu.is.sbapp.student.repository.WorkerRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

import java.util.Optional;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final PositionService positionService;
    private final DepartmentService departmentService;
    private final ValidatorUtil validatorUtil;

    public WorkerService(WorkerRepository workerRepository,PositionService positionService, DepartmentService departmentService,
                         ValidatorUtil validatorUtil) {
        this.workerRepository = workerRepository;
        this.validatorUtil = validatorUtil;
        this.positionService = positionService;
        this.departmentService = departmentService;
    }

    @Transactional
    public Worker addWorker(String fullName, String email, long id_department, long id_position) {
        var department = departmentService.findDepartment(id_department);
        var position = positionService.findPosition(id_position);
        var worker = new Worker(fullName, email);
        worker.setPosition(position);
        worker.setDepartment(department);
        //final Worker worker = new Worker(fullName, email);
        validatorUtil.validate(worker);
        return workerRepository.save(worker);
    }

    @Transactional
    public WorkerDto addWorker(WorkerDto workerDto) {
        return new WorkerDto(addWorker(workerDto.getName(), workerDto.getEmail(), workerDto.getDepartment(), workerDto.getPosition()));
    }


    @Transactional(readOnly = true)
    public Worker findWorker(Long id) {
        final Optional<Worker> worker = workerRepository.findById(id);
        return worker.orElseThrow(() -> new WorkerNotFoundException(id));

    }

    @Transactional(readOnly = true)
    public List<Worker> findAllWorkers() {
        return workerRepository.findAll();
    }

    @Transactional
    public Worker updateWorker(Long id, String fullName, String email) {
        final Worker currentWorker = findWorker(id);
        currentWorker.setFullName(fullName);
        currentWorker.setEmail(email);
        validatorUtil.validate(currentWorker);
        return workerRepository.save(currentWorker);
    }

    @Transactional
    public Worker deleteWorker(Long id) {
        final Worker currentWorker = findWorker(id);
        workerRepository.delete(currentWorker);
        return currentWorker;
    }

    @Transactional
    public void deleteAllWorkers() {
        workerRepository.deleteAll();
    }

    @Transactional
    public Worker addDepartment(Long idC, Long idA) {
        final Worker currentWorker = findWorker(idC);
        final Department currentDepartment = departmentService.findDepartment(idA);

        if (currentDepartment.toString().isEmpty() || currentWorker.toString().isEmpty()) {
            throw new IllegalArgumentException("Address or Client is null or empty");
        }
        currentWorker.setDepartment(currentDepartment);
        currentDepartment.addWorker(currentWorker);
        return currentWorker;
    }

    @Transactional
    public Worker addPositionToWorker(Long idW, Long idP) {
        final Position currentPosition = positionService.findPosition(idP);
        final Worker currentWorker = findWorker(idW);

        if (currentPosition.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Position is null or empty");
        }
        currentWorker.setPosition(currentPosition);
        return currentWorker;
    }
   @Transactional
    public Optional<List> avgSal(/*float workid*/) {
        return workerRepository.avgSal(/*(int)workid*/);
    }

}
