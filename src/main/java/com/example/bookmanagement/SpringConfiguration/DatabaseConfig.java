package com.example.bookmanagement.SpringConfiguration;

import com.example.bookmanagement.Models.DatabaseMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    private String databaseName;

    private String user;

    private String password;

    private String IP;

    private String databaseDriver;

    private String instanceName = System.getenv("CLOUD_SQL_CONNECTION_NAME");

    @Value("${cloudSQL.databaseIP}")
    public void setDatabaseIP(String IP) {
        System.err.println("Collecting database IP: " + IP);
        this.IP = IP;
    }

    @Value("${cloudSQL.dbname}")
    public void setDatabaseName(String databaseName) {
        System.err.println("Collecting database name: " + databaseName);
        this.databaseName = databaseName;
    }

    @Value("${cloudSQL.user}")
    public void setUser(String user) {
        System.err.println("Collecting user: " + user);
        this.user = user;
    }

    @Value("${cloudSQL.pass}")
    public void setPassword(String password) {
        System.err.println("Collecting password: " + password);
        this.password = password;
    }

    @Value("${cloudSQL.driver}")
    public void setDatabaseDriver(String databaseDriver) {
        System.err.println("Collecting driver: " + databaseDriver);
        this.databaseDriver = databaseDriver;
    }

    @Bean
    public DatabaseMetadata getMetadata() {
        System.err.println("Initializing database metadata bean.");
        return new DatabaseMetadata(databaseName, user, password, IP, databaseDriver, instanceName);
    }
}
