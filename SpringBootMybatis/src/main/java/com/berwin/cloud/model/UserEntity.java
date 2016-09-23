package com.berwin.cloud.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zengnianmei
 * @version 1.0, 2016/9/8.
 */
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    //用户名
    private String name;

    private String email;

    //性别 0: 女 1: 男 2:其他
    private int sex;

    //电话
    private String tel;

    //密码
    private String password;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
