package com.capco.technologies.living.domain.model;

/**
 * Created by sachin on 07/01/18.
 */

public interface IExpenseItem extends Expense {

    String getName();

    IExpenseHeader getHeader();

    void setHeader(IExpenseHeader header);

    double getAmount();

    void setName(String name);

    void setAmount(double amount);
}
