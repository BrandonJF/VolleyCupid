
package com.brandonjf.volleycupid.okclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("when_taken")
    @Expose
    private Integer whenTaken;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("lower_right_x")
    @Expose
    private Integer lowerRightX;
    @SerializedName("lower_right_y")
    @Expose
    private Integer lowerRightY;
    @SerializedName("ordinal")
    @Expose
    private Integer ordinal;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("when_uploaded")
    @Expose
    private Integer whenUploaded;
    @SerializedName("upper_left_x")
    @Expose
    private Integer upperLeftX;
    @SerializedName("upper_left_y")
    @Expose
    private Integer upperLeftY;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("picid")
    @Expose
    private String picid;

    /**
     * 
     * @return
     *     The path
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * @param path
     *     The path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 
     * @return
     *     The whenTaken
     */
    public Integer getWhenTaken() {
        return whenTaken;
    }

    /**
     * 
     * @param whenTaken
     *     The when_taken
     */
    public void setWhenTaken(Integer whenTaken) {
        this.whenTaken = whenTaken;
    }

    /**
     * 
     * @return
     *     The type
     */
    public Integer getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The lowerRightX
     */
    public Integer getLowerRightX() {
        return lowerRightX;
    }

    /**
     * 
     * @param lowerRightX
     *     The lower_right_x
     */
    public void setLowerRightX(Integer lowerRightX) {
        this.lowerRightX = lowerRightX;
    }

    /**
     * 
     * @return
     *     The lowerRightY
     */
    public Integer getLowerRightY() {
        return lowerRightY;
    }

    /**
     * 
     * @param lowerRightY
     *     The lower_right_y
     */
    public void setLowerRightY(Integer lowerRightY) {
        this.lowerRightY = lowerRightY;
    }

    /**
     * 
     * @return
     *     The ordinal
     */
    public Integer getOrdinal() {
        return ordinal;
    }

    /**
     * 
     * @param ordinal
     *     The ordinal
     */
    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    /**
     * 
     * @return
     *     The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * 
     * @param caption
     *     The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * 
     * @return
     *     The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The whenUploaded
     */
    public Integer getWhenUploaded() {
        return whenUploaded;
    }

    /**
     * 
     * @param whenUploaded
     *     The when_uploaded
     */
    public void setWhenUploaded(Integer whenUploaded) {
        this.whenUploaded = whenUploaded;
    }

    /**
     * 
     * @return
     *     The upperLeftX
     */
    public Integer getUpperLeftX() {
        return upperLeftX;
    }

    /**
     * 
     * @param upperLeftX
     *     The upper_left_x
     */
    public void setUpperLeftX(Integer upperLeftX) {
        this.upperLeftX = upperLeftX;
    }

    /**
     * 
     * @return
     *     The upperLeftY
     */
    public Integer getUpperLeftY() {
        return upperLeftY;
    }

    /**
     * 
     * @param upperLeftY
     *     The upper_left_y
     */
    public void setUpperLeftY(Integer upperLeftY) {
        this.upperLeftY = upperLeftY;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The picid
     */
    public String getPicid() {
        return picid;
    }

    /**
     * 
     * @param picid
     *     The picid
     */
    public void setPicid(String picid) {
        this.picid = picid;
    }

}
