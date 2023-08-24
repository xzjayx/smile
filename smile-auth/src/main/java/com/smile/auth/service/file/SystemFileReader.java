package com.smile.auth.service.file;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p> Description:打成jar包后，从外面读取文件</p>
 * <p> CreationTime: 2023/1/12 11:40
 *
 */
public class SystemFileReader extends AbstractFileReader{
    @Override
    public InputStream readByPath(String path) throws IOException {
        File file = FileUtils.getFile(path);
        if (file == null || !file.isFile()) {
            return null;

        }
        return FileUtils.openInputStream(file);
    }

    @Override
    protected String buildPath(String originalPath) throws IOException {
        return "." +  originalPath;
    }
}

