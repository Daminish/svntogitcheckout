package com.capco.technologies.living.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sachin on 07/01/18.
 */

public class DashboardMenu implements Parcelable {

    public int icon;
    public String title;
    public String desc;
    public int id;

    public DashboardMenu(int id, int icon, String title, String desc) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.desc = desc;
    }


    public DashboardMenu() {
    }

    protected DashboardMenu(Parcel in) {
        id = in.readInt();
        icon = in.readInt();
        title = in.readString();
        desc = in.readString();
    }

    public static final Creator<DashboardMenu> CREATOR = new Creator<DashboardMenu>() {
        @Override
        public DashboardMenu createFromParcel(Parcel in) {
            return new DashboardMenu(in);
        }

        @Override
        public DashboardMenu[] newArray(int size) {
            return new DashboardMenu[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(icon);
        parcel.writeString(title);
        parcel.writeString(desc);
    }
}
