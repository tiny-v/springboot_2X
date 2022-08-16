package com.spring2x.demo.test.util;


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


    private final String basePackage = "com.tinyv.demo.test";

    /**
     * 执行反编译命令， 返回值为指定方法的反编译内容
     * @param classPath
     * @return
     */
    private String getClassContent(String classPath){
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
     * 从类的反编译内容中，抽取指定方法的内容
     * @param methodName
     * @return
     */
    private String getMethodContent(String classContent, String methodName){
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
     * @return
     */
    private boolean checkAssert(String mContent){
        try{
            return mContent.contains("invokestatic") && mContent.contains("org/junit/Assert.");
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 加载类
     * @param name
     * @return
     */
    private Class getBasicClass(String name) {
        Class clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    /**
     * 获取需要校验的有没有断言的Case
     * 1. 如果类上加了@Ignore注解, 不需要校验
     * 2. 方法上没有@Test注解，或者加了@Ignore注解 或者 方法使用excepted进行异常断言， 不需要校验
     * @param classPath
     * @return
     */
    private ArrayList getJunitMethods(String classPath){
        Class clazz = getBasicClass(classPath);
        if(clazz==null){
            return null;
        }
        //如果类上加了@Ignore注解, 则认为不需要校验
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
            Annotation noAssert = method.getAnnotation(NoAssert.class);
            //方法上加了@Test注解 && 方法没有使用excepted进行异常断言 && 方法没加@Ignore注解 -> 认为是需要校验的case
            if(mAnnotation!=null && mAnnotation.toString().contains("expected=class org.junit.Test$None)") && mIgnore==null && noAssert==null){
                methodNames.add(method.getName());
            }
        }
        return methodNames;
    }

    private List<String> getClassName(String packageName, boolean childPackage) {
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

    private List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
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

    private void execute() {
        String split = basePackage.split("[.]")[0];
        try {
            List<String> files = getClassName(basePackage, Boolean.TRUE);
            int m_number = 0;
            for (String file : files) {
                String classPath = (split+file.split(split)[1]).replace("\\", ".").replace(".class", "");
                ArrayList<String> methodNames = getJunitMethods(classPath);
                if(methodNames==null || methodNames.size()==0){
                    continue;
                }
                String c_content = getClassContent(file);
                logger.info("===== 类名:[{}]",  classPath);
                for(String methodName : methodNames){
                    String m_content = getMethodContent(c_content, methodName);
                    if(!checkAssert(m_content)){
                        logger.info("========== No.{}, 方法名:[{}]", ++m_number, methodName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        logger.info("============================== Start ===================================");
        long startTime = System.currentTimeMillis();
        new CheckHappyTest().execute();
        logger.info("============================== End ===================================");
        logger.info("============================== Total Cost: {} seconds", (System.currentTimeMillis()-startTime)/1000);
    }

}
