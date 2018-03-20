package com.capco.technologies.living.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by sachin on 07/01/18.
 */

public class ExpenseHeader implements IExpenseHeader, Parcelable {

    public String name;
    public String alternativeName;
    public double amount;

    protected ExpenseHeader(Parcel in) {
        name = in.readString();
        alternativeName = in.readString();
        amount = in.readDouble();
    }

    public static final Creator<ExpenseHeader> CREATOR = new Creator<ExpenseHeader>() {
        @Override
        public ExpenseHeader createFromParcel(Parcel in) {
            return new ExpenseHeader(in);
        }

        @Override
        public ExpenseHeader[] newArray(int size) {
            return new ExpenseHeader[size];
        }
    };

    public List<ExpenseItem> getExpenseItems() {
        return expenseItems;
    }

    public void setExpenseItems(List<ExpenseItem> expenseItems) {
        this.expenseItems = expenseItems;
    }

    public List<ExpenseItem> expenseItems;


    public ExpenseHeader() {
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAlternativeName() {
        return alternativeName;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(alternativeName);
        parcel.writeDouble(amount);
    }
}
