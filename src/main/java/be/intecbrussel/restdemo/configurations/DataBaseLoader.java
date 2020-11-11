package be.intecbrussel.restdemo.configurations;

import be.intecbrussel.restdemo.entities.Employee;
import be.intecbrussel.restdemo.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class DataBaseLoader {

    private static final Logger log = LoggerFactory.getLogger(DataBaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args ->{
        log.info("Preloading" + repository.save( new Employee("David","Developer")));
        log.info("Preloading" + repository.save(new Employee("Wilson", "Music-maker")));
            
        };
    }
}
