package com.kang.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 工具类
 *
 * @author klr
 * @create 2020-03-07-13:29
 */
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    //    直接读取resources下的xml
    //    放入static中初始就加载
    //    每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的。
    //    SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder 获得。
    //    而 SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先定制的 Configuration 的实例构建出 SqlSessionFactory 的实例。
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(true);
    }
}
