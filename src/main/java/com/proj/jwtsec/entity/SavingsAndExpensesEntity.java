package com.proj.jwtsec.entity;

import com.proj.jwtsec.user.Statement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavingsAndExpensesEntity {

	private int savings;
	private String expense_name;
	private int expense_amt;
	
}
