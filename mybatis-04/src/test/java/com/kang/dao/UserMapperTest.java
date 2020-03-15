package com.kang.dao;

import com.kang.pojo.User;
import com.kang.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

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
}
