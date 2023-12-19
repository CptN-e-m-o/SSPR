package ru.ulstu.is.sbapp.student.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.student.controller.DepartmentDto;
import ru.ulstu.is.sbapp.student.controller.DopDepartmentDto;
import ru.ulstu.is.sbapp.student.model.Department;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ulstu.is.sbapp.student.model.Department;
import ru.ulstu.is.sbapp.student.model.Position;
import ru.ulstu.is.sbapp.student.model.Worker;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

    //Добавить метод который позволяет получить среднюю зарплату сотрудников в отделе
    /*@Query(value = "select c.model, o.firstName, o.lastName, s.name from Car c inner join Owner o on c.owner.id = o.id inner join STO s on c.sto.id = s.id where c.price >= :price1 and c.price <= :price2")
    Optional<Object> takeByPrice(@Param("price1") int price1,
                                 @Param("price2") int price2);
*/
   /* @Query(value = "select new ru.ulstu.is.sbapp.student.controller.DopDepartmentDto(p.name, p.salaryPerHour) from Position p inner join Worker w on w.position.id = p.id inner join Department d on w.department.id = :depid")
    List<DopDepartmentDto> avgSalary(@Param("depid") Long depid);*/
}
