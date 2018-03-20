package com.capco.living.controller;

import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.capco.living.model.Budgeting;
import com.capco.living.model.MonthlyExpenses;
import com.capco.living.service.BudgetingService;
import com.capco.living.vo.BugetingResponseVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("living/budgeting")
public class BudgetingController {
	
	@Autowired
	 private BudgetingService budgetingService;
	 
	 @RequestMapping(value = "/",method=RequestMethod.POST, headers = "Accept=application/json", consumes = "application/json")
     	public @ResponseBody  BugetingResponseVO  calculateBudget(@RequestBody Budgeting budgeting,HttpServletResponse httpstatus ) {
		JSONObject json = new JSONObject();
		
		ObjectMapper mapper=new ObjectMapper();
		double emi	=0.0;
		
		BugetingResponseVO responseVO = new BugetingResponseVO();
        try {
            	double total_income=budgeting.getTotal_income();
        	    double mortgage_amount=budgeting.getMortgage_amount();
        	    int mortgage_term=budgeting.getMortgage_term();
                MonthlyExpenses monthly_expenses = mapper.readValue(budgeting.getMonthlyExpenses().toString(), MonthlyExpenses.class);
                System.out.println(monthly_expenses.toString());  // invokes myObject.toString()
                emi=budgetingService.budgetingCalculateService(total_income, mortgage_amount,mortgage_term, monthly_expenses);
                if(emi==0.0)
                 {
                    responseVO.setStatus(200);
                    responseVO.setStatusMessage("Your are not eligible for loan.Your Total Income is less than your monthly expenses");
                }
                else
                {
                System.out.println("Expected EMI : "+emi);		  
             
                //generating response
                responseVO.setTotal_income(budgeting.getTotal_income());
                responseVO.setMortgage_amount(budgeting.getMortgage_amount());
                responseVO.setMortgage_term(budgeting.getMortgage_term());
                responseVO.setMonthlyExpenses(budgeting.getMonthlyExpenses());
                responseVO.setEmi(emi);
                responseVO.setAccountBalance(323332);
                responseVO.setStatus(200);
                responseVO.setStatusMessage("Your are Eligible for loan");
                } 
        }
        catch (Exception ex) 
        {
        	    System.out.println("exception:"+ex);
            	json.put("Status Code", 401);    	
		}
     
           		return responseVO;
    }
	 
}
