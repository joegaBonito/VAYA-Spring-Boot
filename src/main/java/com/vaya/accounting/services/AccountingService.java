package com.vaya.accounting.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vaya.accounting.domain.Accounting;

public interface AccountingService {
	public List<Accounting> list();
	public void save(Accounting accounting);
	public double getTotalCurrentBudget(Pageable pageable);
	public void getBalanceAfterTeamExpense(Pageable pageable);
	public void getBalanceAfterEtcExpense();
	public void getBalanceAfterMeetingExpense();
	public void getBalanceAfterRetreatExpense();
	public void getBalanceAfterIncome();
	
}
