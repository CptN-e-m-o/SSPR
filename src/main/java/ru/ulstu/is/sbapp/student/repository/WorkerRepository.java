package ru.ulstu.is.sbapp.student.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.student.model.Worker;
import ru.ulstu.is.sbapp.student.model.Position;
import ru.ulstu.is.sbapp.student.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface WorkerRepository extends JpaRepository<Worker, Long>{

   @Query(value = "select w.id, d.avgSalary from Department d left join Worker w on w.department.id=d.id")
   Optional<List> avgSal(/*@Param("workerid") int workerid*/);

}
