package com.capco.technologies.living.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sachin on 07/01/18.
 */

public class MortgageRequirement implements Parcelable {


    protected MortgageRequirement(Parcel in) {
        isJoinApplicant = in.readByte() != 0;
        estimatedAmount = in.readDouble();
        term = in.readInt();
        nationalId = in.readString();
    }

    public static final Creator<MortgageRequirement> CREATOR = new Creator<MortgageRequirement>() {
        @Override
        public MortgageRequirement createFromParcel(Parcel in) {
            return new MortgageRequirement(in);
        }

        @Override
        public MortgageRequirement[] newArray(int size) {
            return new MortgageRequirement[size];
        }
    };

    public boolean isJoinApplicant() {
        return isJoinApplicant;
    }

    public void setJoinApplicant(boolean joinApplicant) {
        isJoinApplicant = joinApplicant;
    }

    public double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    boolean isJoinApplicant;
    double estimatedAmount;
    int term;
    String nationalId;


    public MortgageRequirement() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (isJoinApplicant ? 1 : 0));
        parcel.writeDouble(estimatedAmount);
        parcel.writeInt(term);
        parcel.writeString(nationalId);
    }
}
