package com.example.bookmanagement.Models;

import java.util.Objects;

public class DatabaseMetadata {
    private final String databaseName;

    private final String user;

    private final String password;

    private final String databasePublicIP;

    private final String driverName;

    private final String instanceName;

    private final String projectId;

    private final String bucketName;

    private final boolean isAppEngine;

    public DatabaseMetadata(String databaseName, String user, String password, String databasePublicIP, String driverName, String instanceName, String projectId, String bucketName) {
        this.databaseName = databaseName;
        this.user = Objects.requireNonNullElse(user, "root");
        this.password = password;
        this.databasePublicIP = databasePublicIP;
        this.driverName = Objects.requireNonNullElse(driverName, "");
        this.instanceName = instanceName;
        this.projectId = projectId;
        this.bucketName = bucketName;
        isAppEngine = Objects.nonNull(instanceName);
    }

    public String getDatabaseConnectionString() throws NullPointerException {
        if (Objects.isNull(databaseName) || Objects.isNull(instanceName) && isAppEngine || Objects.isNull(password)) {
            throw new NullPointerException(String.format("Database name (%s), instance Name (%s) or password (%s) is null.", databaseName, instanceName, password));
        }
        if (isAppEngine) {
            String connectionString = "jdbc:mysql:///%s?cloudSqlInstance=%s&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=%s&password=%s";
            return String.format(connectionString, databaseName, instanceName, user, password);
        }
        return String.format("jdbc:mysql://%s/%s", databasePublicIP, databaseName);
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driverName;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public boolean checkAppEngineEnvironment() {
        return isAppEngine;
    }

    @Override
    public String toString() {
        return "DatabaseMetadata{" +
                "databaseName='" + databaseName + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", databasePublicIP='" + databasePublicIP + '\'' +
                ", driverName='" + driverName + '\'' +
                ", instanceName='" + instanceName + '\'' +
                ", projectId='" + projectId + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", isAppEngine=" + isAppEngine +
                '}';
    }
}
