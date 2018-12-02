package com.wetrackthensa.chimmy.gatracker;

public class updates {
    public  updates()
    {

    }
    public updates(String source, String body, String time, String title, String agency) {
        this.source = source;
        this.body = body;
        this.time = time;
        this.title = title;
        this.agency = agency;
    }

    public String getsourceh() {
        return source;
    }

    public void setsource(String source) {
        this.source = source;
    }

    public String getbodyh() {
        return body;
    }

    public void setbody(String body) {
        this.body = body;
    }

    public String gettimeh() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public String gettitleh() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getagencyh() {
        return agency;
    }

    public void setagency(String agency) {
        this.agency = agency;
    }

    String source, body, title, time, agency;


}
