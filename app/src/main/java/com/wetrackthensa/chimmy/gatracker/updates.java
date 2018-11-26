package com.wetrackthensa.chimmy.gatracker;

public class updates {
    public  updates()
    {

    }
    public updates(String source, String body) {
        this.source = source;
        this.body = body;
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

    String source,body;


}
