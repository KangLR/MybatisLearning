package com.kang.dao;

import com.kang.pojo.User;
import com.kang.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author klr
 * @create 2020-03-07-14:25
 */
public class UserMapperTest {
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
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            User user=userMapper.getUserById(4);
            System.out.println(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }
    }

    //增删改需要提交事务
    @Test
    public void addUser(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            int user=userMapper.addUser(new User(5, "五", "123456"));
            System.out.println(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.commit();//一定要提交事务
            sqlSession.close();
        }
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            int user=userMapper.updateUser(new User(5, "五", "789123"));
            System.out.println(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.commit();//一定要提交事务
            sqlSession.close();
        }
    }

    @Test
    public void deleteUser(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            int user=userMapper.deleteUser(4);
            System.out.println(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.commit();//一定要提交事务
            sqlSession.close();
        }
    }


    //Map实现插入
    @Test
    public void addUserByMap(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();

        try{
            Map<String, Object> map = new HashMap<>();
            map.put("userid",7);
            map.put("username", "六五");
            map.put("password", "234567");
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            int user=userMapper.addUserByMap(map);
            System.out.println(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.commit();//一定要提交事务
            sqlSession.close();
        }
    }

    @Test
    public void getUserByMap(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();

        try{
            Map<String, Object> map = new HashMap<>();
//            map.put("id",5);
            map.put("username", "%五%");
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<User> user= userMapper.getUserByMap(map);
            for (User user1 : user) {
                System.out.println(user1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.commit();//一定要提交事务
            sqlSession.close();
        }
    }
}
