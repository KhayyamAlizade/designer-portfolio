package com.design.portfolio.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class Util {
    public static void deleteFile(File obj) {
        try {
            log.info("Deleting file: {}", obj.getAbsolutePath());
            if (!obj.delete()) {
                throw new IOException("Failed to delete file: " + obj.getAbsolutePath());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
