package com.example.bookmanagement.DataAccessObjects;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StorageRepository {

    private final Storage storage;

    private final String bucketName = "book-app-instance.appspot.com";

    @Autowired
    public StorageRepository(Storage storage) {
        this.storage = storage;
    }

    public String getBucketMetadata(){
        Bucket bucket = storage.get(bucketName);
        return bucket.getName();
    }

    public boolean saveFile(byte[] bytes, String objectName) {
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, bytes);
        return true;
    }

    public byte[] getFile(String objectName) {
        BlobId blobId = BlobId.of(bucketName, objectName);
        Blob blob = storage.get(blobId);
        if (blob.exists()) {
            return blob.getContent();
        }
        return null;
    }
}
