package com.banglore.computer.bce;

import java.util.ArrayList;

/**
 * Created by raman on 9/4/17.
 */

public class responseplacement {

    ArrayList<placement> data=new ArrayList<>();

    public class placement{


      public  placement()
        {}

        String sname;

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPackag() {
            return packag;
        }

        public void setPackag(String packag) {
            this.packag = packag;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        String company;
        String packag;
        String pic;


        public placement(String sname, String company, String packag, String pic) {
            this.sname = sname;
            this.company = company;
            this.packag = packag;
            this.pic = pic;
        }
    }






}
