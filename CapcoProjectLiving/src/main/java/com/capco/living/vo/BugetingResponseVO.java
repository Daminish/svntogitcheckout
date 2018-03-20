package com.capco.living.vo;

import org.json.simple.JSONObject;
public class BugetingResponseVO {
	
	private double total_income;
	private double mortgage_amount;
	private int mortgage_term;
    private JSONObject monthlyExpenses = new JSONObject();
    private double emi;
    private int accountBalance;
    private int status;
    private String statusMessage;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
    public int getMortgage_term() {
		return mortgage_term;
	}
	public void setMortgage_term(int mortgage_term) {
		this.mortgage_term = mortgage_term;
	}
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public void setTotal_income(double total_income) {
		this.total_income = total_income;
	}

	@Override
	public String toString() {
		return "BugetingResponseVO [total_income=" + total_income + ", mortgage_amount=" + mortgage_amount
				+ ", mortgage_term=" + mortgage_term + ", monthlyExpenses=" + monthlyExpenses + ", emi=" + emi
				+ ", accountBalance=" + accountBalance + ", status=" + status + ", statusMessage=" + statusMessage
				+ "]";
	}

	public double getMortgage_amount() {
		return mortgage_amount;
	}
    public void setMortgage_amount(double mortgage_amount) {
		this.mortgage_amount = mortgage_amount;
	}


	public BugetingResponseVO(double total_income, double mortgage_amount, int mortgage_term,
			JSONObject monthlyExpenses, double emi, int accountBalance, int status, String statusMessage) {
		super();
		this.total_income = total_income;
		this.mortgage_amount = mortgage_amount;
		this.mortgage_term = mortgage_term;
		this.monthlyExpenses = monthlyExpenses;
		this.emi = emi;
		this.accountBalance = accountBalance;
		this.status = status;
		this.statusMessage = statusMessage;
	}

	public BugetingResponseVO() {
		super();
	}
    public double getEmi() {
		return emi;
	}
    public void setEmi(double emi) {
		this.emi = emi;
	}
    public int getAccountBalance() {
		return accountBalance;
	}
    public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
}
