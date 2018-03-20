package com.capco.living.service;

import com.capco.living.model.MonthlyExpenses;

public interface BudgetingService {
	public double budgetingCalculateService(double total_income,double mortgage_amount, int mortgage_term, MonthlyExpenses monthly_expenses);
}
