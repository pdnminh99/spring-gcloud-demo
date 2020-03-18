package com.example.bookmanagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@SpringBootApplication
//@Configuration
public class BookManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookManagementApplication.class, args);
    }

    @GetMapping("/")
    public String test() {
        return "DB name: " + DB_NAME + "\nDB user: " + DB_USER + "\nCONNECTION_NAME: " + CLOUD_SQL_CONNECTION_NAME;
    }

    private final String DB_NAME = System.getenv("DB_NAME");
    private final String DB_USER = System.getenv("DB_USER");
    private final String DB_PASS = System.getenv("DB_PASS");
    private final String CLOUD_SQL_CONNECTION_NAME = System.getenv("CLOUD_SQL_CONNECTION_NAME");
//    private final String FULL_CONNECTION_NAME = "jdbc:mysql://34.87.80.228/BOOKSTORE";
    private final String FULL_CONNECTION_NAME = String
        .format("jdbc:mysql:///%s?cloudSqlInstance=%s&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=%s&password=%s",
        DB_NAME, CLOUD_SQL_CONNECTION_NAME, DB_USER, DB_PASS);
    @Bean
    public DataSource mysqlDataSource() {
         return DataSourceBuilder.create()
         .url(FULL_CONNECTION_NAME)
         .driverClassName("com.mysql.cj.jdbc.Driver")
         .password("admin")
         .username("root")
         .build();

//        // The configuration object specifies behaviors for the connection pool.
//        HikariConfig config = new HikariConfig();
//        // Configure which instance and what database user to connect with.
//        config.setJdbcUrl("jdbc:mysql:///" + DB_NAME);
//        // config.setJdbcUrl(FULL_CONNECTION_NAME);
//        config.setUsername(DB_USER);
//        config.setPassword(DB_PASS);
//        //		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//
//        // For Java users, the Cloud SQL JDBC Socket Factory can provide authenticated
//        // connections
//        // See https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory for
//        // details.
//        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
//        config.addDataSourceProperty("cloudSqlInstance", CLOUD_SQL_CONNECTION_NAME);
//        config.addDataSourceProperty("useSSL", "false");
//
//        // Initialize the connection pool using the configuration object.
//        return new HikariDataSource(config);
    }

}
