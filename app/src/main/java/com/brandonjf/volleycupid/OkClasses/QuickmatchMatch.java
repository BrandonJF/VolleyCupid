
package com.brandonjf.volleycupid.okclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class QuickmatchMatch {

    @SerializedName("online")
    @Expose
    private Boolean online;
    @SerializedName("userinfo")
    @Expose
    private com.brandonjf.volleycupid.okclasses.Userinfo userinfo;
    @SerializedName("percentages")
    @Expose
    private com.brandonjf.volleycupid.okclasses.Percentages percentages;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("thumbs")
    @Expose
    private List<com.brandonjf.volleycupid.okclasses.Thumb> thumbs = new ArrayList<com.brandonjf.volleycupid.okclasses.Thumb>();

    /**
     * @return The online
     */
    public Boolean getOnline() {
        return online;
    }

    /**
     * @param online The online
     */
    public void setOnline(Boolean online) {
        this.online = online;
    }

    /**
     * @return The userinfo
     */
    public com.brandonjf.volleycupid.okclasses.Userinfo getUserinfo() {
        return userinfo;
    }

    /**
     * @param userinfo The userinfo
     */
    public void setUserinfo(com.brandonjf.volleycupid.okclasses.Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    /**
     * @return The percentages
     */
    public com.brandonjf.volleycupid.okclasses.Percentages getPercentages() {
        return percentages;
    }

    /**
     * @param percentages The percentages
     */
    public void setPercentages(com.brandonjf.volleycupid.okclasses.Percentages percentages) {
        this.percentages = percentages;
    }

    /**
     * @return The userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid The userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The thumbs
     */
    public List<com.brandonjf.volleycupid.okclasses.Thumb> getThumbs() {
        return thumbs;
    }

    /**
     * @param thumbs The thumbs
     */
    public void setThumbs(List<com.brandonjf.volleycupid.okclasses.Thumb> thumbs) {
        this.thumbs = thumbs;
    }

}
