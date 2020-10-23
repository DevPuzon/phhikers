package com.softwaresolution.phhikers.Pojo;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppDatas {

    @SerializedName("mountain_list")
    @Expose
    private ArrayList<MountainList> mountainList = null;
    @SerializedName("guides_tips")
    @Expose
    private ArrayList<GuidesTip> guidesTips = null;
    @SerializedName("emergency_guides")
    @Expose
    private ArrayList<EmergencyGuide> emergencyGuides = null;
    @SerializedName("checklist")
    @Expose
    private ArrayList<Checklist> checklist = null;
    @SerializedName("overnight_hikes")
    @Expose
    private ArrayList<OvernightHike> overnightHikes = null;
    @SerializedName("longhikes")
    @Expose
    private ArrayList<Longhike> longhikes = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public AppDatas() {
    }

    /**
     *
     * @param longhikes
     * @param mountainList
     * @param emergencyGuides
     * @param checklist
     * @param guidesTips
     * @param overnightHikes
     */
    public AppDatas(ArrayList<MountainList> mountainList, ArrayList<GuidesTip> guidesTips, ArrayList<EmergencyGuide> emergencyGuides, ArrayList<Checklist> checklist, ArrayList<OvernightHike> overnightHikes, ArrayList<Longhike> longhikes) {
        super();
        this.mountainList = mountainList;
        this.guidesTips = guidesTips;
        this.emergencyGuides = emergencyGuides;
        this.checklist = checklist;
        this.overnightHikes = overnightHikes;
        this.longhikes = longhikes;
    }

    public ArrayList<MountainList> getMountainList() {
        return mountainList;
    }

    public void setMountainList(ArrayList<MountainList> mountainList) {
        this.mountainList = mountainList;
    }

    public ArrayList<GuidesTip> getGuidesTips() {
        return guidesTips;
    }

    public void setGuidesTips(ArrayList<GuidesTip> guidesTips) {
        this.guidesTips = guidesTips;
    }

    public ArrayList<EmergencyGuide> getEmergencyGuides() {
        return emergencyGuides;
    }

    public void setEmergencyGuides(ArrayList<EmergencyGuide> emergencyGuides) {
        this.emergencyGuides = emergencyGuides;
    }

    public ArrayList<Checklist> getChecklist() {
        return checklist;
    }

    public void setChecklist(ArrayList<Checklist> checklist) {
        this.checklist = checklist;
    }

    public ArrayList<OvernightHike> getOvernightHikes() {
        return overnightHikes;
    }

    public void setOvernightHikes(ArrayList<OvernightHike> overnightHikes) {
        this.overnightHikes = overnightHikes;
    }

    public ArrayList<Longhike> getLonghikes() {
        return longhikes;
    }

    public void setLonghikes(ArrayList<Longhike> longhikes) {
        this.longhikes = longhikes;
    }

}