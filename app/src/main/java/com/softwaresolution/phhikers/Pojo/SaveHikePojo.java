package com.softwaresolution.phhikers.Pojo;

import java.util.Date;

public class SaveHikePojo {
    public MountainList mountain;
    public String exp;
    public String date;
    public String hikeTime;
    public String urlLoc;
    public String urlLocStarted;

    public MountainList getMountain() {
        return mountain;
    }

    public void setMountain(MountainList mountain) {
        this.mountain = mountain;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHikeTime() {
        return hikeTime;
    }

    public void setHikeTime(String hikeTime) {
        this.hikeTime = hikeTime;
    }

    public String getUrlLoc() {
        return urlLoc;
    }

    public void setUrlLoc(String urlLoc) {
        this.urlLoc = urlLoc;
    }

    public String getUrlLocStarted() {
        return urlLocStarted;
    }

    public void setUrlLocStarted(String urlLocStarted) {
        this.urlLocStarted = urlLocStarted;
    }

    public SaveHikePojo(MountainList mountain, String exp, String date, String hikeTime, String urlLoc, String urlLocStarted) {
        this.mountain = mountain;
        this.exp = exp;
        this.date = date;
        this.hikeTime = hikeTime;
        this.urlLoc = urlLoc;
        this.urlLocStarted = urlLocStarted;
    }
}
