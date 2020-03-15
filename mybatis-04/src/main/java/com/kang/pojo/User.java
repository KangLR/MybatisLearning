package com.kang.pojo;

/**
 * 实体类
 *
 * @author klr
 * @create 2020-03-07-14:02
 */
public class User {
    private int id;
    private String name;
    private String password;
    //private String pwd;
    // 变成password，这样字段就不一致了

    //不定义无参不能new空对象
    public User(){
    }
    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
