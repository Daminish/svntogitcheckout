package com.capco.living.model;

public class MonthlyExpenses {

	private double creadit_card;
	private double loan_rapayment;
	private double electricity_bill;
	private double cinema;
	private double maintenance;
	private double child_care;
	private double fitness;
	private double fuel;
	public MonthlyExpenses() {
		super();
	}
	public MonthlyExpenses(double creadit_card, double loan_rapayment, double electricity_bill, double cinema, double maintenance,
			double child_care, double fitness, double fuel) {
		super();
		this.creadit_card = creadit_card;
		this.loan_rapayment = loan_rapayment;
		this.electricity_bill = electricity_bill;
		this.cinema = cinema;
		this.maintenance = maintenance;
		this.child_care = child_care;
		this.fitness = fitness;
		this.fuel = fuel;
	}
	public double getCreadit_card() {
		return creadit_card;
	}
	public void setCreadit_card(double creadit_card) {
		this.creadit_card = creadit_card;
	}
	public double getLoan_rapayment() {
		return loan_rapayment;
	}
	public void setLoan_rapayment(double loan_rapayment) {
		this.loan_rapayment = loan_rapayment;
	}
	public double getElectricity_bill() {
		return electricity_bill;
	}
	public void setElectricity_bill(double electricity_bill) {
		this.electricity_bill = electricity_bill;
	}
	public double getCinema() {
		return cinema;
	}
	public void setCinema(double cinema) {
		this.cinema = cinema;
	}
	public double getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(double maintenance) {
		this.maintenance = maintenance;
	}
	public double getChild_care() {
		return child_care;
	}
	public void setChild_care(double child_care) {
		this.child_care = child_care;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	public double getFuel() {
		return fuel;
	}
	public void setFuel(double fuel) {
		this.fuel = fuel;
	}
	@Override
	public String toString() {
		return "MonthlyExpenses [creadit_card=" + creadit_card + ", loan_rapayment=" + loan_rapayment
				+ ", electricity_bill=" + electricity_bill + ", cinema=" + cinema + ", maintenance=" + maintenance
				+ ", child_care=" + child_care + ", fitness=" + fitness + ", fuel=" + fuel + "]";
	}

}
