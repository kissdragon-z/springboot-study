package com.example.springbootstudy.factory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SelfClassLoader extends ClassLoader {

    private static final String rootJavaPath = "";
    private static final String rootClassPath = "com/example/self";

    private static final int bufferSize = 4096;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] classData = getClassData(name);

        if (null == classData) {

            throw new ClassNotFoundException();
        } else {

            return defineClass(name, classData, 0, classData.length);

        }

    }

    private byte[] getClassData(String className) {

        String path = classNameToPath(className);

        return getFileData(new File(path));

    }

    private byte[] getFileData(File file) {

        try {

            InputStream ins = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[bufferSize];

            int bytesNumRead = 0;

            while ((bytesNumRead = ins.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesNumRead);
            }

            return outputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    private String classNameToPath(String className) {

        return rootClassPath + File.separator + className.replace(".", File.separator) + ".class";

    }

    public synchronized List<Class> fetchSelfClasses() throws ClassNotFoundException {

        List<Class> classes = new ArrayList<>();

        File pathFile = new File(rootClassPath);

        File[] files = pathFile.listFiles();

        for (int i = 0; i < files.length; i++) {

            File file = files[i];

            byte[] fileData = getFileData(file);

            this.defineClass(file.getName(), fileData, 0, fileData.length);

            Class<?> cls = this.loadClass(file.getName());

        }

        return classes;

    }

}
