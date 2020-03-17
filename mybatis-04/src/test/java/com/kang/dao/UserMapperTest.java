package com.kang.dao;

import com.kang.pojo.User;
import com.kang.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author klr
 * @create 2020-03-07-14:25
 */
public class UserMapperTest {
    static Logger logger=Logger.getLogger(UserMapperTest.class);
    @Test
    public void getAllUser(){
        //得到sqlSession对象
        SqlSession sqlSession= MybatisUtils.getSqlSession();
        try {
            //执行sql 方式一：getMapper
            UserMapper userMapper =sqlSession.getMapper(UserMapper.class);//得到Dao接口
            List<User> user= userMapper.getUserList();//执行Dao接口里的方法

            //方式二，过时了，不建议使用
            //List<User> userList=sqlSession.selectList("com.kang.dao.UserMapper.getUserList");
            for (User user1 : user) {
                System.out.println(user1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //关闭sqlSession
            sqlSession.close();
        }
    }
    @Test
    public void getUserById(){
        SqlSession sqlSession= MybatisUtils.getSqlSession();
//        logger.info();
//        logger.debug();
        try {
            UserMapper userMapper =sqlSession.getMapper(UserMapper.class);//得到Dao接口
            User user=userMapper.getUserById(2);//执行Dao接口里的方法
            System.out.println(user);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //关闭sqlSession
            sqlSession.close();
        }
    }
    @Test
    public void testLog4j(){
        logger.info("info:进入了testLog4j");
        logger.debug("debug:进入了testLog4j");
        logger.error("error:进入了testLog4j");
    }
    @Test
    public void getUserByLimit(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper user = sqlSession.getMapper(UserMapper.class);
        Map<String,Integer>map=new HashMap<>();
        map.put("startIndex",1);
        map.put("pageSize",4);
        for (User user1 : user.getUserByLimit(map)) {
            System.out.println(user1);
        }
        sqlSession.close();

    }

    @Test
    public void classTest() throws ClassNotFoundException {
//        Class c1 = Class.forName("com.kang.pojo.User");
//        System.out.println(c1);
//        Class c2 = Class.forName("com.kang.pojo.User");
//        Class c3 = Class.forName("com.kang.pojo.User");
//        Class c4 = Class.forName("com.kang.pojo.User");
//        //一个类只有一个Class对象，它们的hashCode（）是相同的
//        System.out.println(c1.hashCode());
//        System.out.println(c2.hashCode());
//        System.out.println(c3.hashCode());
//        System.out.println(c4.hashCode());
        Class c1=Object.class;//类
        Class c2=Comparable.class;//接口
        Class c3=String.class;//字符串
        Class c4=String[].class;
        Class c5=String[][].class;
        Class c6=int[][].class;
        Class c7=Override.class;//注解
        Class c8= ElementType.class;//枚举
        Class c9=Integer.class;//基本数据类型
        Class c10=void.class;//void
        Class c11=Class.class;//Class类

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);
        System.out.println(c10);
        System.out.println(c11);

    }

    @Test
    public void getClassLoader() throws ClassNotFoundException {
        //系统类加载器
        ClassLoader classLoader=ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        //扩展类加载器
        ClassLoader classLoader1=classLoader.getParent();
        System.out.println(classLoader1);
        //引导类加载器，无法直接获取
        ClassLoader classLoader2=classLoader1.getParent();
        System.out.println(classLoader2);

        //测试当前类是由谁加载的
        ClassLoader classLoader3=Class.forName("com.kang.dao.UserMapperTest").getClassLoader();
        System.out.println(classLoader3);
        //测试jdk内置类是由谁加载的
        ClassLoader classLoader4=Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader4);

        //如何获取系统类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));
    }

    //获得类的信息
    @Test
    public void getClassInfo() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1=Class.forName("com.kang.pojo.User");
        //获得类的名字
        String name = c1.getName();
        System.out.println(name);
        System.out.println("===========================");

        //获得类的属性
        //getField只能找到public的，要用getDeclareFields获得所有
        Field[] fields=c1.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("===========================");

        Field[] fields1=c1.getDeclaredFields();
        for (Field field : fields1) {
            System.out.println(field);
        }
        System.out.println("===========================");

        //获得指定的属性
        //Field field=c1.getField("name");//private会报错
        //System.out.println(field);
        Field field1=c1.getDeclaredField("name");
        System.out.println(field1);

        //获得类的方法
        System.out.println("===========================");
        Method[] methods=c1.getMethods();//获得本类及其父类的所有public
        for (Method method : methods) {
            System.out.println("正常的："+method);
        }
        System.out.println("===========================");

        Method[] methods1=c1.getDeclaredMethods();//获得本类的所有方法
        for (Method method : methods1) {
            System.out.println(method);
        }
        System.out.println("===========================");
        //获得指定的方法
        Method method=c1.getMethod("getName",null);
        System.out.println(method);
        Method setName=c1.getMethod("setName", String.class);//因为有重载，所以需要参数String.class
        System.out.println(setName);

        System.out.println("===========================");
        //获得所有的构造器
        Constructor[] constructors = c1.getConstructors();//获得public
        for (Constructor constructor1 : constructors) {
            System.out.println(constructor1);
        }
        Constructor[] declaredConstructors = c1.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println("#"+declaredConstructor);
        }
        //获得指定的构造器
        System.out.println(c1.getDeclaredConstructor( int.class, String.class,String.class));
    }

    //通过反射来动态的创建对象
    @Test
    public void getObjectByReflect() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //获得Class对象
        Class c1 = Class.forName("com.kang.pojo.User");
        //构造一个对象

        //本质上是调用了类的无参构造器初始化对象，把User类的无参构造器删了会报错
        //User o = (User) c1.newInstance();
        //System.out.println(o);

        //实际上是通过构造器创建对象
        User user=(User) c1.getDeclaredConstructor().newInstance();
        System.out.println(user);

        //通过反射调用普通方法
        //c1是Class，并不是通过user这种实例调用方法
        Method setName = c1.getDeclaredMethod("setName", String.class);

        //invoke=调用
        //(对象，“方法的值”)
        //invoke通过反射获取的方法去跑的
        //要知道方法属于哪个对象，因此要传入对象
        setName.invoke(user,"kang");//invoke调用，给个对象传递参数
        System.out.println(user.getName());


        //通过反射操作属性
        User user1 =(User)c1.newInstance();
        Field name=c1.getDeclaredField("name");
        //不能直接操作私有属性
        name.setAccessible(true);//name是private的，要想修改，必须关闭权限检测，方法页适用
        name.set(user1,"kang1");
        System.out.println(user1.getName());
    }

    //分析性能问题
    @Test
    public void AnalyzePerformanceProblems() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //普通方式调用
        User user=new User();
        long startTime=System.currentTimeMillis();
        for(int i=0;i<100000000;i++)
        {user.getName();}
        long endTime=System.currentTimeMillis();
        System.out.println("普通方式执行亿次："+(endTime-startTime)+"ms");
        //通过反射方式调用
        User user1=new User();
        Class c1=user1.getClass();
        Method method1=c1.getDeclaredMethod("getName",null);
        startTime=System.currentTimeMillis();
        for(int i=0;i<100000000;i++)
        {
            method1.invoke(user,null);
        }
        endTime=System.currentTimeMillis();
        System.out.println("反射方式执行亿次："+(endTime-startTime)+"ms");
        //反射关闭检测
        User user2=new User();
        Class c2=user2.getClass();
        Method method=c2.getDeclaredMethod("getName",null);
        method.setAccessible(true);
        startTime=System.currentTimeMillis();
        for(int i=0;i<100000000;i++)
        {
            method.invoke(user,null);
        }
        endTime=System.currentTimeMillis();
        System.out.println("反射方式关闭检测执行亿次："+(endTime-startTime)+"ms");
    }
}



