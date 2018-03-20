package com.capco.technologies.living.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shreyas Bhondve on 1/9/2018.
 */

public class FindHomeMenu implements Parcelable {

    public String name;
    public int image;

    public FindHomeMenu() {
    }

    public FindHomeMenu(String name, int image) {
        this.name = name;
        this.image = image;
    }

    protected FindHomeMenu(Parcel in) {
        name = in.readString();
        image = in.readInt();
    }

    public static final Creator<FindHomeMenu> CREATOR = new Creator<FindHomeMenu>() {
        @Override
        public FindHomeMenu createFromParcel(Parcel in) {
            return new FindHomeMenu(in);
        }

        @Override
        public FindHomeMenu[] newArray(int size) {
            return new FindHomeMenu[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(image);
    }
}
