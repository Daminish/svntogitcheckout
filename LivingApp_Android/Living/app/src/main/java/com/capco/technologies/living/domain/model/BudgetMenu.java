package com.capco.technologies.living.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BudgetMenu implements Parcelable {
    public String name;
    public String value;
    public int image;
    

    public BudgetMenu() {
    }

    public BudgetMenu(String name, String value, int image) {
        this.name = name;
        this.value = value;
        this.image = image;
    }

    protected BudgetMenu(Parcel in) {
        name = in.readString();
        value = in.readString();
        image = in.readInt();
    }

    public static final Creator<BudgetMenu> CREATOR = new Creator<BudgetMenu>() {
        @Override
        public BudgetMenu createFromParcel(Parcel in) {
            return new BudgetMenu(in);
        }

        @Override
        public BudgetMenu[] newArray(int size) {
            return new BudgetMenu[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(value);
        parcel.writeInt(image);
    }
}