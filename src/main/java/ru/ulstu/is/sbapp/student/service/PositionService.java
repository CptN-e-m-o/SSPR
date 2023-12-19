package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.sbapp.student.model.Position;
import ru.ulstu.is.sbapp.student.controller.PositionDto;
import ru.ulstu.is.sbapp.student.repository.PositionRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;
import java.util.Optional;
import java.util.List;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final ValidatorUtil validatorUtil;

    public PositionService(PositionRepository positionRepository,
                         ValidatorUtil validatorUtil) {
        this.positionRepository = positionRepository;
        this.validatorUtil = validatorUtil;
    }


    @Transactional
    public Position addPosition(String name,  Double salaryPerHour) {
        final Position position = new Position(name, salaryPerHour);
        validatorUtil.validate(position);
        return positionRepository.save(position);

    }

    @Transactional(readOnly = true)
    public Position findPosition(Long id) {
        final Optional<Position> position = positionRepository.findById(id);
        return position.orElseThrow(() -> new PositionNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Position> findAllPositions() {
        return positionRepository.findAll();
    }


    @Transactional
    public Position updatePosition(Long id, Double salaryPerHour) {
        final Position currentPosition = findPosition(id);
        currentPosition.setSalaryPerHour(salaryPerHour);
        validatorUtil.validate(currentPosition);
        return positionRepository.save(currentPosition);
    }

    @Transactional
    public PositionDto updatePosition (PositionDto positionDto){
        /*List <Discipline> disciplines = positionDto.getDisciplines().stream()
                .map(discipline -> disciplineService.findDiscipline(discipline.getId())).toList();*/
        return new PositionDto(updatePosition(positionDto.getId(), Double.valueOf(positionDto.getName())));
    }


    @Transactional
    public Position deletePosition(Long id) {
        final Position currentPosition = findPosition(id);
        positionRepository.delete(currentPosition);
        return currentPosition;
    }

    @Transactional
    public void deleteAllPositions() {
        positionRepository.deleteAll();
    }

}
