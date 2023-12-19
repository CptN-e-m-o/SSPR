package ru.ulstu.is.sbapp.student.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.student.model.Position;
import ru.ulstu.is.sbapp.student.model.Worker;
import ru.ulstu.is.sbapp.student.service.PositionService;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/{id}")
    public PositionDto getPosition(@PathVariable Long id) {
        return new PositionDto(positionService.findPosition(id));
    }

    @GetMapping("/")
    public List<PositionDto> getPositions() {
        return positionService.findAllPositions().stream()
                .map(PositionDto::new)
                .toList();
    }

    @PostMapping("/")
    public PositionDto createPosition(@RequestParam("name") String name,
                               @RequestParam("salaryPerHour") Double salaryPerHour) {
        return new PositionDto(positionService.addPosition(name, salaryPerHour));
    }

    @PatchMapping("/{id}")
    public PositionDto updatePosition(@RequestBody @Valid PositionDto positionDto) {
        return positionService.updatePosition(positionDto);
    }

    @DeleteMapping("/{id}")
    public PositionDto deletePosition(@PathVariable Long id) {
        return new PositionDto(positionService.deletePosition(id));
    }
}
