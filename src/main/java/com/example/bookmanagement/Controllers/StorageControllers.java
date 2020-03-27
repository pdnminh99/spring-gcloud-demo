package com.example.bookmanagement.Controllers;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import com.example.bookmanagement.CustomExceptions.BlobNotFoundException;
import com.example.bookmanagement.Models.FileObject;
import com.example.bookmanagement.Services.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/bucket")
public class StorageControllers {

    private final StorageService service;

    @Autowired
    public StorageControllers(StorageService service) {
        this.service = service;
    }

    @GetMapping
    public String getBucketMetadata() {
        return service.getBucketMetadata();
    }

    @PostMapping
    public boolean receiveFile(@RequestParam("file") MultipartFile[] files) throws IOException {
        String name, contentType;
        for (MultipartFile file : files) {
            name = file.getOriginalFilename();
            contentType = file.getContentType();

            System.out.println("Content type: " + contentType);
            System.out.println("Size :" + file.getSize());
            System.out.println("Original fileName: " + name);

            service.saveFile(file.getBytes(), name, contentType);
        }
        return true;
    }

    @GetMapping(value = "{file}")
    public byte[] getFile(HttpServletResponse response, @PathVariable("file") String file) throws BlobNotFoundException {
        FileObject object = service.getFile(file);
        if (Objects.isNull(object)) {
            throw new BlobNotFoundException("Cannot find file name " + file);
        }
        response.setContentType(object.getContentType());
        return object.getBytes();
    }

    @ExceptionHandler(BlobNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleBlobNotFoundException() {
        return "File not exist";
    }

}
