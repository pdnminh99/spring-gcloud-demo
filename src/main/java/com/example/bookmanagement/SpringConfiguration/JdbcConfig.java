package com.example.bookmanagement.SpringConfiguration;

import com.example.bookmanagement.Models.DatabaseMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    private final DatabaseMetadata metadata;

    @Autowired
    public JdbcConfig(DatabaseMetadata metadata) {
        this.metadata = metadata;
    }

    @Bean
    @Scope("singleton")
    @Lazy
    public DataSource mysqlDataSource() {
        System.err.println("Confirming meta data before initialize DataSource.");
        System.err.println(metadata.toString());
        return DataSourceBuilder.create()
                .url(metadata.getDatabaseConnectionString())
                .driverClassName(metadata.getDriver())
                .password(metadata.getPassword())
                .username(metadata.getUser())
                .build();
    }
}
