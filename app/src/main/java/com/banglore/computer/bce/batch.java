package com.banglore.computer.bce;

/**
 * Created by raman on 7/4/17.
 */

public class batch {

    String technology;
    String bdate;
    String btime;

    public batch() {
    }

    public batch(String technology, String bdate, String btime) {
        this.technology = technology;
        this.bdate = bdate;
        this.btime = btime;

    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }
}
