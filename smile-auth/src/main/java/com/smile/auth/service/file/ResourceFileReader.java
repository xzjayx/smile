package com.smile.auth.service.file;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p> Description:读取resource文件夹下的文件</p>
 * <p> CreationTime: 2023/1/12 11:43
 *
 */
public class ResourceFileReader extends AbstractFileReader {
    @Override
    public InputStream readByPath(String path) {
        return ResourceFileReader.class.getResourceAsStream(path);
    }

    @Override
    protected String buildPath(String originalPath) throws IOException {
        return originalPath;
    }
}
