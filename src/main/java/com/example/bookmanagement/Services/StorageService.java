package com.example.bookmanagement.Services;

import com.example.bookmanagement.DataAccessObjects.CloudSQLBookRepository;
import com.example.bookmanagement.DataAccessObjects.StorageRepository;
import com.example.bookmanagement.Models.FileObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    private StorageRepository storageRepository;

    private CloudSQLBookRepository cloudSQLBookRepository;

    @Autowired
    public void setStorageRepository(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Autowired
    public void setCloudSQLBookRepository(CloudSQLBookRepository cloudSQLBookRepository) {
        this.cloudSQLBookRepository = cloudSQLBookRepository;
    }

    public void saveFile(byte[] bytes, String name, String contentType) {
        FileObject object = new FileObject("", name, contentType);
        cloudSQLBookRepository.saveFileMetadata(object);
        System.err.println(object.toString());
        storageRepository.saveFile(bytes, object.getObjectId());
    }

    public FileObject getFile(String file) {
        FileObject object = cloudSQLBookRepository.getFileMetadata(file);
        object.setBytes(storageRepository.getFile(object.getObjectId()));
        return object;
    }

    public String getBucketMetadata() {
        return storageRepository.getBucketMetadata();
    }
}
