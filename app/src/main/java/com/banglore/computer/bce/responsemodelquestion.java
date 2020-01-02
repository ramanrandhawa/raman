package com.banglore.computer.bce;

import java.util.ArrayList;

/**
 * Created by a on 4/19/2017.
 */

public class responsemodelquestion {

    boolean status;
    ArrayList<innerclass> data;

    class innerclass{

        String question;
        String answer;
        String lang;
        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }


        innerclass()
        {

        }

        public innerclass(String question)
        {
            this.question = question;
        }

        public String getQuestion()
        {
            return question;
        }

        public void setQuestion(String question)
        {
            this.question = question;
        }
    }




}
