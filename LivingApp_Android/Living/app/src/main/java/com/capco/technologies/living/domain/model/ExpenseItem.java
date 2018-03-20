package com.capco.technologies.living.domain.model;

/**
 * Created by sachin on 07/01/18.
 */

public class ExpenseItem implements IExpenseItem {

    public String name;
    public double amount;
    public IExpenseHeader header;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IExpenseHeader getHeader() {
        return header;
    }

    @Override
    public void setHeader(IExpenseHeader header) {
        this.header = header;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
