package com.capco.technologies.living.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sachin on 06/01/18.
 */

public class Offer implements Parcelable {

    public int id;
    public String imageUrl;
    public String title;
    public String shortDesctiption;
    public String fullDescription;


    public Offer() {
    }

    protected Offer(Parcel in) {
        id = in.readInt();
        imageUrl = in.readString();
        title = in.readString();
        shortDesctiption = in.readString();
        fullDescription = in.readString();
    }

    public static final Creator<Offer> CREATOR = new Creator<Offer>() {
        @Override
        public Offer createFromParcel(Parcel in) {
            return new Offer(in);
        }

        @Override
        public Offer[] newArray(int size) {
            return new Offer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(imageUrl);
        parcel.writeString(title);
        parcel.writeString(shortDesctiption);
        parcel.writeString(fullDescription);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesctiption() {
        return shortDesctiption;
    }

    public void setShortDesctiption(String shortDesctiption) {
        this.shortDesctiption = shortDesctiption;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }
}
