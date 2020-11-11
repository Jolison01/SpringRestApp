package be.intecbrussel.restdemo.controllers;


import be.intecbrussel.restdemo.entities.Employee;
import be.intecbrussel.restdemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    EmployeeRepository employeeRepository;


    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/employees")
    List<Employee> all(){
        return employeeRepository.findAll();
    }



    @PostMapping("/employees")

    Employee newEmployee(@RequestBody Employee newEmployee){

        return employeeRepository.save(newEmployee);
    }

    // Single item

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // second method of GetMapping

  /*  @GetMapping("employees{id}")
    Employee one(@PathVariable Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()){
            return employeeOptional.get();
        }
        else {
            throw new EmployeeNotFoundException(id);
        }
    }
*/





    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }


    /*
    // second method of @putMapping

    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){

        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            if(newEmployee.getName().isEmpty()) {
                employee.setName(newEmployee.getName());
            }

            if (newEmployee.getRole().isEmpty()) {

                employee.setRole(newEmployee.getRole());
            }
            return employeeRepository.save(employee);
        }
        else {
            newEmployee.setId(id);
            return employeeRepository.save(newEmployee);
        }
    }


*/

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }



}




