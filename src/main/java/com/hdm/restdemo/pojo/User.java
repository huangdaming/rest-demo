package com.hdm.restdemo.pojo;

/**
 * @ClassName: User
 * @description:
 * @author: huangdaming
 * @Date: 2020-04-06 17:46
 */
public class User {
    private Long id;
    private int age;
    private String name;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
