package com.proj.jwtsec.user;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "UserInfo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator = "Random5digitgenerator")
	@GenericGenerator(name="Random5digitgenerator",strategy="com.sandy.jwtsec.randomdigitgenerator.RandomDigitGenerator")
	private int accno;
	@Column(unique=true)
	private String userEmail;//this should ref the userEmail from _user table
	private String firstName;
	private String lastName;
	private int balance;
	public UserInfo(String firstName, String lastName,String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmail=email;
	}
	

}
