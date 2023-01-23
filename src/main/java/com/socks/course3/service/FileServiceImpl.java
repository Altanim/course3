package com.socks.course3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    @Value("${name.of.data.file}")
    private String fileDataName;
    @Value("${path.to.data.file}")
    private String fileDataPath;

    @Override
    public boolean saveFileSocks(String json) {
        try {
            cleanDataFileSocks();
            Files.writeString(Path.of(fileDataPath, fileDataName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFileSocks() {
        try {
            return Files.readString(Path.of(fileDataPath, fileDataName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getDataFile() {
        return new File(fileDataPath + "/" + fileDataName);
    }
    @Override
    public boolean cleanDataFileSocks() {
        try {
            Files.deleteIfExists(Path.of(fileDataPath, fileDataName));
            Files.createFile(Path.of(fileDataPath, fileDataName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public Path CreateTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(fileDataPath), "tempfile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
