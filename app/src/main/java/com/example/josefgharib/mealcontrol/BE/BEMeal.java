package com.example.josefgharib.mealcontrol.BE;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by josefgharib on 01/04/16.
 */
public class BEMeal implements Parcelable
{
    private int mId;
    private String mTitle;
    private String mDescription;
    private boolean mDone;


    public BEMeal(int Id, String Title, String Description, boolean Done)
    {
        mId = Id;
        mTitle = Title;
        mDescription = Description;
        mDone = Done;

    }

    // Parcelable constructor
    public BEMeal(Parcel source)
    {
        mId = source.readInt();
        mTitle = source.readString();
        mDescription = source.readString();
        mDone = source.hasFileDescriptors();
    }
    // --- Getters/Setters
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
    public boolean ismDone() {
        return mDone;
    }

    public void setmDone(boolean mDone) {
        this.mDone = mDone;
    }

    public int getmId() {return mId;}

    public void setmId(int mId) {this.mId = mId;}
    // ----------------------


    // The creator of the parcelable constructor
    public static final Parcelable.Creator<BEMeal> CREATOR = new Parcelable.Creator<BEMeal>() {

        public BEMeal createFromParcel(Parcel in) {
            return new BEMeal(in);
        }

        public BEMeal[] newArray(int size) {
            return new BEMeal[size];
        }
    };


    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(getmId());
        out.writeString(getmTitle());
        out.writeString(getmDescription());
        out.writeByte((byte) (ismDone() ? 1 : 0));
    }
}
