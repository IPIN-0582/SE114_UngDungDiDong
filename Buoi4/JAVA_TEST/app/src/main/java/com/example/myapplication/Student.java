package com.example.myapplication;

import java.io.Serializable;

public class Student implements Serializable {
    private int ID;
    private String Name;
    private int YearBrith;

    public Student(int ID, String Name, int YearBrith){
        this.ID = ID;
        this.Name = Name;
        this.YearBrith = YearBrith;
    }

    public int getID() {
        return ID;
    }

    public int getYearBrith() {
        return YearBrith;
    }

    public String getName() {
        return Name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setYearBrith(int yearBrith) {
        YearBrith = yearBrith;
    }
}

