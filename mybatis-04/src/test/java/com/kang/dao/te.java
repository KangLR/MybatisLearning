package com.kang.dao;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * @author klr
 * @create 2020-03-17-12:02
 */
public class te {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c1=Class.forName("com.kang.dao.student");
        //通过反射获得所有注解
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        //获取注解的value的值
        Table annotation =(Table) c1.getAnnotation(Table.class);
        System.out.println(annotation.value());

        //获得类指定的注解
        Field declaredField = c1.getDeclaredField("name");
        field annotation1 = declaredField.getAnnotation(field.class);
        System.out.println(annotation1.columnName());
        System.out.println(annotation1.type());
        System.out.println(annotation1.length());

    }
}

@Table("db_student")
class student{
    @field(columnName = "db_id",type = "int",length = 10)
    private int id;
    @field(columnName = "db_age",type = "int",length = 10)
    private int age;
    @field(columnName = "db_name",type = "varchar",length = 3)
    private String name;

    public student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public student(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}

//类名的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}

//属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface field{
    String columnName();
    String type();
    int length();
}