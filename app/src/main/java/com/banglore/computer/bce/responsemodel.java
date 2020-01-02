package com.banglore.computer.bce;

import java.util.ArrayList;

/**
 * Created by raman on 8/4/17.
 */

public class responsemodel {
    public responsemodel() {

    }

    ArrayList<innerdata> data=new ArrayList<>();

public innerdata idata;

    public class innerdata
    {
        String technology;

        public innerdata() {
        }

        public innerdata(String tecnology, String bdate, String btime) {
            this.technology = tecnology;
            this.bdate = bdate;
            this.btime = btime;
        }

        public String getTecnology() {
            return technology;
        }

        public void setTecnology(String tecnology) {
            this.technology = tecnology;
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

        String bdate;
        String btime;

    }




}
