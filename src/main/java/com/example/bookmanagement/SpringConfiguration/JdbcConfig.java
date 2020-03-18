package com.example.bookmanagement.SpringConfiguration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class JdbcConfig {
    private final String DB_NAME = System.getenv("DB_NAME");
    private final String DB_USER = System.getenv("DB_USER");
    private final String DB_PASS = System.getenv("DB_PASS");
    private final String CLOUD_SQL_CONNECTION_NAME = System.getenv("CLOUD_SQL_CONNECTION_NAME");
    private final String CONNECTION_IP = "jdbc:mysql://34.87.80.228/BOOKSTORE";

    private final String FULL_CONNECTION_NAME = String
            .format("jdbc:mysql:///%s?cloudSqlInstance=%s&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=%s&password=%s",
                    DB_NAME, CLOUD_SQL_CONNECTION_NAME, DB_USER, DB_PASS);

    @Bean
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create()
                .url(CLOUD_SQL_CONNECTION_NAME != null ? FULL_CONNECTION_NAME : CONNECTION_IP)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .password("admin")
                .username("root")
                .build();
    }
}
