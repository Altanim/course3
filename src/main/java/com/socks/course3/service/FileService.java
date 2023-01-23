package com.socks.course3.service;

import java.io.File;
import java.nio.file.Path;

public interface FileService {

    boolean saveFileSocks(String json);

    String readFileSocks();

    boolean cleanDataFileSocks();

    File getDataFile();


    Path CreateTempFile(String suffix);
}
