package com.softwaresolution.phhikers.Pojo;

public class CusCheckList {
    private String name;
    private boolean checked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public CusCheckList() {
    }

    public CusCheckList(String name, boolean checked) {
        this.name = name;
        this.checked = checked;
    }
}
