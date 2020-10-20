package com.crossyf.dubbo.springtest.test2;

import com.crossyf.dubbo.springtest.dto.Book;
import com.crossyf.dubbo.springtest.utils.ExcelUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class testReflect {
    public static final String className = "com.crossyf.dubbo.springtest.dto.Book";

    public static void main(String[] args) {
        reflectNewInstance();

        reflectPrivateField();

        reflectPrivateMethod();
    }

    /**
     * 创建对象
     */
    public static void reflectNewInstance() {

        try {
            Class<?> clz = Class.forName(className);
            System.out.println(clz.getName());

            Book book = (Book)clz.newInstance();
            book.setName("一本正经");
            book.setAuthor("狐狸");

            System.out.println(book.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 反射私有属性
     */
    public static void reflectPrivateField(){
        try {
            Class<?> clz = Class.forName(className);
            Field[] fields = clz.getDeclaredFields();
            for (Field field: fields){
                if(field.isAnnotationPresent(ExcelUtil.class)){
                    System.out.println(field.getName());
                    System.out.println(field.getDeclaredAnnotation(ExcelUtil.class).value());
                }

        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射私有属性
     */
    public static void reflectPrivateMethod(){
        try {
            Class<?> clz = Class.forName(className);
            Method[] methods = clz.getDeclaredMethods();
            for (Method method: methods){
                System.out.println(method.getName());
            }

            Method method = clz.getDeclaredMethod("declaredMethod",int.class);
            method.setAccessible(true);
            Book book = (Book) clz.newInstance();
            book.setName("一本正经");
            book.setAuthor("狐狸");

            String str = (String) method.invoke(book, 1);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
