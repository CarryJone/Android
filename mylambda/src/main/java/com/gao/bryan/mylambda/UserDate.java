package com.gao.bryan.mylambda;

/**
 * Created by bryan on 2017/3/30.
 */

public class UserDate  {

    private String name;
    private String age;
    private static UserDate mUserdate = new UserDate();
    public  static UserDate getInstence(){

        return mUserdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
