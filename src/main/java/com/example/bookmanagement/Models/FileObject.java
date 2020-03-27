package com.example.bookmanagement.Models;

public class FileObject {
    private byte[] bytes;

    private String contentType;

    private String name;

    private String objectId;

    public FileObject(byte[] bytes) {
        this.bytes = bytes;
    }

    public FileObject(String objectId, String name, String contentType) {
        this.objectId = objectId;
        this.name = name;
        this.contentType = contentType;
    }

    public FileObject(byte[] bytes, String name, String contentType) {
        this.bytes = bytes;
        this.name = name;
        this.contentType = contentType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "FileObject{" +
                "contentType='" + contentType + '\'' +
                ", name='" + name + '\'' +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
