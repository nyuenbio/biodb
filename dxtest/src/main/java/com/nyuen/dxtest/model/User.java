package com.nyuen.dxtest.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.userid
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    private Integer userid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.username
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.userpassword
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    private String userpassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.useable
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    private Boolean useable;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.userid
     *
     * @return the value of user.userid
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.userid
     *
     * @param userid the value for user.userid
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.username
     *
     * @return the value of user.username
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.username
     *
     * @param username the value for user.username
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.userpassword
     *
     * @return the value of user.userpassword
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    public String getUserpassword() {
        return userpassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.userpassword
     *
     * @param userpassword the value for user.userpassword
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.useable
     *
     * @return the value of user.useable
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    public Boolean getUseable() {
        return useable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.useable
     *
     * @param useable the value for user.useable
     *
     * @mbg.generated Fri Nov 01 11:00:03 CST 2019
     */
    public void setUseable(Boolean useable) {
        this.useable = useable;
    }
}