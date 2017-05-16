package com.vaya.accounting.services;

import java.util.List;

import com.vaya.accounting.domain.Accounting;

public interface AccountingService {
	public List<Accounting> list();
	public void save(Accounting accounting);
	public double getTotalCurrentBudget();
	public void getBalanceAfterTeamExpense();
	public void getBalanceAfterEtcExpense();
	public void getBalanceAfterMeetingExpense();
	public void getBalanceAfterRetreatExpense();
	public void getBalanceAfterIncome();
}
