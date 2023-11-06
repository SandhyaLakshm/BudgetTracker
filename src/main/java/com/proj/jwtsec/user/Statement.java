package com.proj.jwtsec.user;


import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Statement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int s_no;
	private int accno;
	private int savings;
	private String expense_name;
	private int expense_amt;
	private int balance;
	
	@CreationTimestamp()
	private Date date;
	
	public Statement(int accno, int savings, String expense_name, int expense_amt) {
		super();
		this.accno = accno;
		this.savings = savings;
		this.expense_name = expense_name;
		this.expense_amt = expense_amt;
	}
	
}
