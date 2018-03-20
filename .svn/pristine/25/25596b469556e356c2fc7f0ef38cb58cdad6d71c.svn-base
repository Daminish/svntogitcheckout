package com.capco.living.model;

import org.json.simple.JSONObject;

public class Budgeting {
	
	private double total_income;
	private double mortgage_amount;
	private int mortgage_term;
    private JSONObject monthlyExpenses = new JSONObject();


	public Budgeting(double total_income, double mortgage_amount, int mortgage_term, JSONObject monthlyExpenses) {
		super();
		this.total_income = total_income;
		this.mortgage_amount = mortgage_amount;
		this.mortgage_term = mortgage_term;
		this.monthlyExpenses = monthlyExpenses;
	}
	public Budgeting() {
		super();
	}
    public int getMortgage_term() {
		return mortgage_term;
	}
	public void setMortgage_term(int mortgage_term) {
		this.mortgage_term = mortgage_term;
	}
	public void setMonthlyExpenses(JSONObject monthlyExpenses) {
		this.monthlyExpenses = monthlyExpenses;
	}
	public JSONObject getMonthlyExpenses() {
		return this.monthlyExpenses;
	}
	public double getTotal_income() {
		return total_income;
	}
    public void setTotal_income(double total_income) {
		this.total_income = total_income;
	}
    
    @Override
	public String toString() {
		return "Budgeting [total_income=" + total_income + ", mortgage_amount=" + mortgage_amount + ", mortgage_term="
				+ mortgage_term + ", monthlyExpenses=" + monthlyExpenses + "]";
	}
	public double getMortgage_amount() {
		return mortgage_amount;
	}
    public void setMortgage_amount(double mortgage_amount) {
		this.mortgage_amount = mortgage_amount;
	}
	
   
}
