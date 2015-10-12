
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
    private Userinfo userinfo;
    @SerializedName("percentages")
    @Expose
    private Percentages percentages;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("thumbs")
    @Expose
    private List<Thumb> thumbs = new ArrayList<Thumb>();

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
    public Userinfo getUserinfo() {
        return userinfo;
    }

    /**
     * @param userinfo The userinfo
     */
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    /**
     * @return The percentages
     */
    public Percentages getPercentages() {
        return percentages;
    }

    /**
     * @param percentages The percentages
     */
    public void setPercentages(Percentages percentages) {
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
    public List<Thumb> getThumbs() {
        return thumbs;
    }

    /**
     * @param thumbs The thumbs
     */
    public void setThumbs(List<Thumb> thumbs) {
        this.thumbs = thumbs;
    }

}
