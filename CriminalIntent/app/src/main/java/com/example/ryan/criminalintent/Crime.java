package com.example.ryan.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Ryan on 6/4/2017.
 */

public class Crime extends Object {
    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mDate = new Date();
        mID = UUID.randomUUID();
    }

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public boolean getSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
