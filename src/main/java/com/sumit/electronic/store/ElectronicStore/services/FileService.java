package com.sumit.electronic.store.ElectronicStore.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    String uploadFile(MultipartFile file, String path) throws IOException;
    InputStream getResource(String pah, String name) throws FileNotFoundException;

}
