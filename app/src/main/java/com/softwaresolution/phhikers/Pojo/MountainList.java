package com.softwaresolution.phhikers.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MountainList {

    @SerializedName("point")
    @Expose
    private String point;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("elevation")
    @Expose
    private String elevation;
    @SerializedName("difficulty")
    @Expose
    private String difficulty;
    @SerializedName("image_src")
    @Expose
    private String imageSrc;

    /**
     * No args constructor for use in serialization
     *
     */
    public MountainList() {
    }

    /**
     *
     * @param elevation
     * @param difficulty
     * @param name
     * @param location
     * @param imageSrc
     * @param point
     */
    public MountainList(String point, String name, String location, String elevation, String difficulty, String imageSrc) {
        super();
        this.point = point;
        this.name = name;
        this.location = location;
        this.elevation = elevation;
        this.difficulty = difficulty;
        this.imageSrc = imageSrc;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

}