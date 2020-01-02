package com.banglore.computer.bce;

import java.util.ArrayList;

/**
 * Created by a on 4/20/2017.
 */

public class responsepics {



    ArrayList<innerclass > data;

    class innerclass
    {

        String fn;

        public innerclass(String fn) {
            this.fn = fn;
        }

        public String getFn() {
            return fn;
        }

        public void setFn(String fn) {
            this.fn = fn;
        }
    }

}
