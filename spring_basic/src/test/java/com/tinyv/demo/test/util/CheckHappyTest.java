package com.tinyv.demo.test.util;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author tiny_v
 * @date 2022/3/23.
 *
 * 功能： 检查快乐的单元测试，即没有断言的断言测试
 */
public class CheckHappyTest {

    private static Logger logger = LoggerFactory.getLogger(CheckHappyTest.class);


    /**
     * 反编译，获取文件内容
     * @param classPath
     * @return
     */
    public String getClassContent(String classPath){
        StringBuilder sb = new StringBuilder();
        String cmd = "javap -c "+classPath;
        try {
            //执行命令
            Process p = Runtime.getRuntime().exec(cmd);
            //获取输出流，并包装到BufferedReader中
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\r\n");
            }
            p.waitFor();
        } catch(IOException e){
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        return sb.toString();
    }



    /**
     * 执行反编译命令， 返回值为指定方法的反编译内容
     * @param methodName
     * @return
     */
    public String getMethodContent(String classContent, String methodName){
        StringBuilder sb = new StringBuilder();
        String[] lines = classContent.split("\r\n");
        boolean print = false;
        for(String line : lines){
            if(line.contains(methodName)){
                print = true;
            }
            if(StringUtils.isBlank(line)){
                print = false;
            }
            if(print){
                sb.append(line).append("\r\n");
            }
        }
        return sb.toString();
    }

    /**
     * 校验是否有Assert断言
     * @param mContent
     * @param method
     * @return
     */
    public boolean checkAssert(String mContent, String method){
        try{
            return mContent.contains("invokestatic") && mContent.contains("Assert.");
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public static List<String> getClassName(String packageName, boolean childPackage) {
        List<String> fileNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        if (url != null) {
            String type = url.getProtocol();
            if (type.equals("file")) {
                fileNames = getClassNameByFile(url.getPath(), null, childPackage);
            }
        }
        return fileNames;
    }

    private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
        List<String> myClassName = new ArrayList<>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                if (childPackage) {
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
                }
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    myClassName.add(childFilePath);
                }
            }
        }
        return myClassName;
    }

    /**
     * 加载类
     * @param name
     * @return
     */
    public Class getBasicClass(String name) {
        Class clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public void test(String backPackage) {
        String split = "com";
        try {
            List<String> files = getClassName(backPackage, Boolean.TRUE);
            for (String file : files) {
                String classPath = (split+file.split(split)[1]).replace("\\", ".").replace(".class", "");
                ArrayList<String> methodNames = getJunitMethods(classPath);
                if(methodNames!=null && methodNames.size()>0){
                   String c_content = getClassContent(file);
                   for(String methodName : methodNames){
                       String m_content = getMethodContent(c_content, methodName);
                       logger.info("类名:[{}], 方法名:[{}], 有断言:[{}]", classPath, methodName, checkAssert(m_content, methodName));

                   }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList getJunitMethods(String classPath){
        Class clazz = getBasicClass(classPath);
        if(clazz==null){
            return null;
        }
        Annotation cIgnore = clazz.getAnnotation(Ignore.class);
        if(cIgnore!=null){
            return null;
        }
        Method[] methods = clazz.getMethods();
        if(clazz.getMethods()==null || clazz.getMethods().length==0){
            return null;
        }
        ArrayList<String> methodNames = new ArrayList();
        for (Method method : methods) {
            Annotation mAnnotation = method.getAnnotation(Test.class);
            Annotation mIgnore = method.getAnnotation(Ignore.class);
            if(mAnnotation!=null && mAnnotation.toString().contains("expected=class org.junit.Test$None)") && mIgnore==null){
                methodNames.add(method.getName());
            }
        }
        return methodNames;
    }

    public static void main(String[] args){
        new CheckHappyTest().test("com.tinyv.demo.test");
    }


}
