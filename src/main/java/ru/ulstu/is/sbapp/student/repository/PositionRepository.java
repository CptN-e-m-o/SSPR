package ru.ulstu.is.sbapp.student.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.student.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{
}
