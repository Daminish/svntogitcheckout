package com.capco.technologies.living.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by sachin on 07/01/18.
 */

public class Budget implements Parcelable {
    MortgageRequirement requirement;
    Income income;

    public MortgageRequirement getRequirement() {
        return requirement;
    }

    public void setRequirement(MortgageRequirement requirement) {
        this.requirement = requirement;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public List<ExpenseHeader> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseHeader> expenses) {
        this.expenses = expenses;
    }

    List<ExpenseHeader> expenses;


    public Budget() {
    }


    protected Budget(Parcel in) {
        requirement = in.readParcelable(MortgageRequirement.class.getClassLoader());
        income = in.readParcelable(Income.class.getClassLoader());
        expenses = in.createTypedArrayList(ExpenseHeader.CREATOR);
    }

    public static final Creator<Budget> CREATOR = new Creator<Budget>() {
        @Override
        public Budget createFromParcel(Parcel in) {
            return new Budget(in);
        }

        @Override
        public Budget[] newArray(int size) {
            return new Budget[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(requirement, i);
        parcel.writeParcelable(income, i);
        parcel.writeTypedList(expenses);
    }
}
