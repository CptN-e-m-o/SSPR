package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.student.model.Department;
import ru.ulstu.is.sbapp.student.model.Worker;
import ru.ulstu.is.sbapp.student.service.WorkerService;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;



import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/{id}")
    public WorkerDto getWorker(@PathVariable Long id) {
        return new WorkerDto(workerService.findWorker(id));
    }

    @GetMapping("/")
    public List<WorkerDto> getWorkers() {
        return workerService.findAllWorkers().stream()
                .map(WorkerDto::new)
                .toList();
    }


    @PostMapping("/")
    public WorkerDto createWorker(@RequestBody @Valid WorkerDto workerDto) {
        return workerService.addWorker(workerDto);
    }

    @PatchMapping("/{id}")
    public WorkerDto updateWorker(@PathVariable Long id,
                                  @RequestParam("fullName") String fullName,
                                  @RequestParam("email") String email) {
        return new WorkerDto(workerService.updateWorker(id, fullName, email));
    }

    @DeleteMapping("/{id}")
    public WorkerDto deleteClient(@PathVariable Long id) {
        return new WorkerDto(workerService.deleteWorker(id));
    }

    @GetMapping("/work_average_salary")
    public List<Object> avgSal(/*@RequestParam(value="id") float id*/) {
        return Collections.singletonList(workerService.avgSal(/*id*/));
    }

    /*@PostMapping("/{idC}/add/{idT}")
    public WorkerDto addPositionToWorker(@RequestParam("idC") Long idC,
                                       @RequestParam("idT") Long idT) {
        return new WorkerDto(workerService.addPositionToWorker(idC, idT));
    }


    @PostMapping("/{idC}/addA/{idA}")
    public WorkerDto addDepartment(@RequestParam("idC") Long idC,
                                @RequestParam("idA") Long idA) {
        return new WorkerDto(workerService.addDepartment(idC, idA));
    }*/

}
