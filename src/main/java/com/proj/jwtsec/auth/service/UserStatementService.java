package com.proj.jwtsec.auth.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.proj.jwtsec.auth.exception.NegMoneyException;
import com.proj.jwtsec.auth.repository.StatementRepository;
import com.proj.jwtsec.auth.repository.UserInfoRepository;
import com.proj.jwtsec.entity.SavingsAndExpensesEntity;

import com.proj.jwtsec.user.Statement;
import com.proj.jwtsec.user.UserInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserStatementService {

	private final StatementRepository repo;
	private final UserInfoRepository userInfoRepo;
	
	public int setStatement(int accno,SavingsAndExpensesEntity entity) throws NegMoneyException {
		if((entity.getSavings()<0) || (entity.getExpense_amt()<0)) {
			throw new NegMoneyException("Money cannot be negative");
		}
		Statement stmt=new Statement(accno,entity.getSavings(),
		entity.getExpense_name(),entity.getExpense_amt());
		UserInfo user=userInfoRepo.findById(accno).get();
		int current_balance=user.getBalance();
		int newbalance=current_balance-entity.getExpense_amt()+entity.getSavings();
		if(newbalance>=0) {
			stmt.setBalance(newbalance);
		}
		else {
			throw new NegMoneyException("Insufficient balance");
			}
		stmt.setBalance(newbalance);
		repo.save(stmt);
		return newbalance;
			
		}
		
	
}
