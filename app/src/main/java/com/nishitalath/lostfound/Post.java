package com.nishitalath.lostfound;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Post {
    private String lname, lphone, litemname, llocation, lmsg, lphotos;

    public Post() {
    }

    public Post(String lname, String lphone, String litemname, String llocation, String lmsg, String lphotos) {
        this.lname = lname;
        this.lphone = lphone;
        this.litemname = litemname;
        this.llocation = llocation;
        this.lmsg = lmsg;
        this.lphotos = lphotos;
    }

    public String getLname() {
        return lname;
    }

    public String getLphone() {
        return lphone;
    }

    public String getLitemname() {
        return litemname;
    }

    public String getLlocation() {
        return llocation;
    }

    public String getLmsg() {
        return lmsg;
    }

    public String getLphotos() {
        return lphotos;
    }
}
