package be.intecbrussel.restdemo.repositories;

import be.intecbrussel.restdemo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
