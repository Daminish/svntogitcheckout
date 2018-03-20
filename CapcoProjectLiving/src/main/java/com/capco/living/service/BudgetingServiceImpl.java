package com.capco.living.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capco.living.dao.BudgetingDAO;
import com.capco.living.model.MonthlyExpenses;


@Service
@Transactional
public class BudgetingServiceImpl implements BudgetingService {

	@Autowired
	BudgetingDAO  budgetingDAO;

	@Override
	public double budgetingCalculateService(double total_income, double mortgage_amount, int mortgage_term,
			MonthlyExpenses monthly_expenses) {
		return budgetingDAO.budgetingCalculate(total_income,mortgage_amount,mortgage_term, monthly_expenses);
	}


	



}