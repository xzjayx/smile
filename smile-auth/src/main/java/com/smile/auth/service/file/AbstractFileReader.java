package com.smile.auth.service.file;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p> Description:AbstractFileReader</p>
 * <p> CreationTime: 2023/1/12 15:50
 * file读取暂未用到
 *
 */
@Slf4j
public abstract class AbstractFileReader {
    private AbstractFileReader next;

    public void setNext(AbstractFileReader next) {
        this.next = next;
    }

    /**
     * 读取文件
     *
     * @param originalPath 原始路径。在这里是 类名/方法名.json
     * @return inputStream
     * @throws IOException 异常
     */
    public InputStream readFile(String originalPath) throws IOException {
        String path = buildPath(originalPath);
        InputStream inputStream = readByPath(path);
        if (inputStream == null && next != null) {
            return next.readFile(originalPath);
        } else if (inputStream != null) {
            log.info("读取的文件路径为：{}", path);
            return inputStream;
        } else {
            log.warn("{}未找到文件！", originalPath);
            return null;
        }
    }


    protected abstract InputStream readByPath(String path) throws IOException;

    protected abstract String buildPath(String originalPath) throws IOException;


}
