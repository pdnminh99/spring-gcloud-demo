package com.example.bookmanagement.SpringConfiguration;

import com.example.bookmanagement.Models.DatabaseMetadata;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Configuration
public class JdbcConfig {

    private final DatabaseMetadata metadata;

    @Autowired
    public JdbcConfig(DatabaseMetadata metadata) {
        this.metadata = metadata;
    }

    @Bean
    @Scope("singleton")
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

    @Bean
    @Scope("singleton")
    public Storage getStorage() throws IOException {
        if (metadata.checkAppEngineEnvironment()) {
            return StorageOptions.getDefaultInstance().getService();
        }
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(currentPath + "\\private\\book-app-instance-bb7e69c7f42a.json"));
        return StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId(metadata.getProjectId())
                .build()
                .getService();
    }


}
