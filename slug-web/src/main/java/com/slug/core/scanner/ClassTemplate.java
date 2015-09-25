package com.slug.core.scanner;

import com.slug.utils.ClassUtils;
import com.slug.utils.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author zhw
 * @version 0.1  15/9/25
 */
public abstract class ClassTemplate {


    protected final String packageName;

    protected ClassTemplate(String packageName) {
        this.packageName = packageName;

    }


    public final List<Class<?>> getClassList() {
        List<Class<?>> classList = new ArrayList<Class<?>>();


        try {

            // get url resource from package
            Enumeration<URL> urls = ClassUtils.getClassLoader().getResources(packageName.replace(".", "/"));

            //traverse url resource

            while (urls.hasMoreElements()) {

                URL url = urls.nextElement();

                if (url != null) {
                    //obtain protocol (file or jar)
                    String protocol = url.getProtocol();

                    if (protocol.equals("file")) {

                        String packagePath = url.getPath().replaceAll("%20", "  ");
                        addClass(classList, packagePath, packageName);
                    } else if (protocol.equals("jar")) {

                        //if the class is in jar ,then parse the entry in jar
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();

                        JarFile jarFile = jarURLConnection.getJarFile();

                        Enumeration<JarEntry> jarEntries = jarFile.entries();

                        while (jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();

                            //if the entry is class
                            if (jarEntryName.equals(".class")) {
                                //get the className
                                String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                // add class
                                Class<?> cls = ClassUtils.loadClass(className, true);
                                if (checkAddClass(cls)) {
                                    classList.add(cls);
                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            //todo add log
            e.printStackTrace();
        }

        return classList;
    }


    private void addClass(List<Class<?>> classList, String packagePath, String packageName) {

        // obtain the class in package path
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return false;
            }
        });

        //traverse the files
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                // obtain the classname
                String className = fileName.substring(0, fileName.lastIndexOf(','));
                if (!StringUtils.isEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                // add class
                Class<?> cls = ClassUtils.loadClass(className, true);
                if (checkAddClass(cls)) {
                    classList.add(cls);
                }
            } else {
                // get sub package
                String subPackagePath = fileName;
                if (!StringUtils.isEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }

                String subPackageName = fileName;

                if (!StringUtils.isEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }

                //递归调用
                addClass(classList, subPackagePath, subPackageName);
            }
        }


    }


    public abstract boolean checkAddClass(Class<?> cls);

}
