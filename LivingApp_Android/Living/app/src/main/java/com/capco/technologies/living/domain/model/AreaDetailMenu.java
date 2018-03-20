package com.capco.technologies.living.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shreyas Bhondve on 1/9/2018.
 */

public class AreaDetailMenu implements Parcelable {

    public String name;
    public int image;

    public AreaDetailMenu() {
    }

    public AreaDetailMenu(String name, int image) {
        this.name = name;
        this.image = image;
    }

    protected AreaDetailMenu(Parcel in) {
        name = in.readString();
        image = in.readInt();
    }

    public static final Creator<AreaDetailMenu> CREATOR = new Creator<AreaDetailMenu>() {
        @Override
        public AreaDetailMenu createFromParcel(Parcel in) {
            return new AreaDetailMenu(in);
        }

        @Override
        public AreaDetailMenu[] newArray(int size) {
            return new AreaDetailMenu[size];
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
