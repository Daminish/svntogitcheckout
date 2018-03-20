package com.capco.technologies.living.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sachin on 07/01/18.
 */

public class Income implements Parcelable {
    double grossSalary;
    double otherIncome;


    public Income() {
    }

    protected Income(Parcel in) {
        grossSalary = in.readDouble();
        otherIncome = in.readDouble();
        monthlyIncome = in.readDouble();
    }

    public static final Creator<Income> CREATOR = new Creator<Income>() {
        @Override
        public Income createFromParcel(Parcel in) {
            return new Income(in);
        }

        @Override
        public Income[] newArray(int size) {
            return new Income[size];
        }
    };

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(double otherIncome) {
        this.otherIncome = otherIncome;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    double monthlyIncome;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(grossSalary);
        parcel.writeDouble(otherIncome);
        parcel.writeDouble(monthlyIncome);
    }
}
