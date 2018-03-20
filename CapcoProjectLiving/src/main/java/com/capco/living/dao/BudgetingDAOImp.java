package com.capco.living.dao;

import org.springframework.stereotype.Repository;
import com.capco.living.model.MonthlyExpenses;

@Repository
public class BudgetingDAOImp implements BudgetingDAO{
	
	@Override
   	public double budgetingCalculate(  double total_income,double mortage_amount,int mortgage_term,MonthlyExpenses monthly_expenses) {
	
	    	double expenses=monthly_expenses.getCreadit_card()+monthly_expenses.getLoan_rapayment()+monthly_expenses.getElectricity_bill()+monthly_expenses.getCinema()+monthly_expenses.getMaintenance()+monthly_expenses.getChild_care()+monthly_expenses.getFitness()+monthly_expenses.getFuel();
	    	
	    	if(expenses>total_income) {
	    		return 0.0;
	    	}
	    	double saving=total_income-expenses;
	    	int principle_amount=(int) ((saving*65)/100);
	    	double emi=(principle_amount*2*(1+8)^mortgage_term)/((1+2)^(mortgage_term-1));
	    	
	    	return emi;
	}
}
