package com.mahfuz.mywordlist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IslamMha on 9/10/2014.
 */
public class Word{
    String value ;
    String meaning;
    String comment;
    String tag ;
    int id ;
    int rate ;
    Word(){
        this.rate = 0 ;
    }
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

}
