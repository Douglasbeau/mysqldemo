package com.ssj.mysqldemo.util;

import org.springframework.boot.system.ApplicationHome;

import java.io.File;

public class FileUtil {
    public static File getAppHome() {
        ApplicationHome home = new ApplicationHome(FileUtil.class);
        return home.getSource().getParentFile();
    }
}
