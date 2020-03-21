package com.example.bookmanagement;

import com.example.bookmanagement.Models.DatabaseMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableJpaRepositories
@EntityScan
@SpringBootApplication
public class BookManagementApplication {

    final DatabaseMetadata metadata;

    @Autowired
    public BookManagementApplication(DatabaseMetadata metadata) {
        this.metadata = metadata;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookManagementApplication.class, args);
    }

    @GetMapping("/")
    public String test() {
        return metadata.toString();
    }

}
