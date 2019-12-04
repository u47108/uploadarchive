package com.roytuts.controller;
import java.io.InputStream;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
public class FileUploadRestController {
    private static final Logger logger = Logger.getLogger(FileUploadRestController.class.getName());
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<String> uploadData(@RequestPart("file") MultipartFile file, @RequestPart("msg") String msg) throws Exception {
        if (file == null) {
            throw new RuntimeException("You must select the a file for uploading + msg");
        }
        InputStream inputStream = file.getInputStream();
        String originalName = file.getOriginalFilename() +" "+ msg;
        String name = file.getName();
        String contentType = file.getContentType();
        long size = file.getSize();
        logger.info("inputStream: " + inputStream);
        logger.info("originalName: " + originalName);
        logger.info("name: " + name);
        logger.info("contentType: " + contentType);
        logger.info("size: " + size);
        // Do processing with uploaded file data in Service layer
        return new ResponseEntity<String>(originalName, HttpStatus.OK);
    }
}