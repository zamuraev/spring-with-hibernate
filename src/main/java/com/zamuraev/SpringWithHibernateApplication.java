package com.zamuraev;

import com.zamuraev.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class SpringWithHibernateApplication implements CommandLineRunner {

    private final CourseRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringWithHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
