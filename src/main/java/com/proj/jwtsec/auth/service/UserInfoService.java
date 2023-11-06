package com.proj.jwtsec.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.proj.jwtsec.auth.repository.StatementRepository;
import com.proj.jwtsec.auth.repository.UserInfoRepository;
import com.proj.jwtsec.auth.repository.UserRepository;
import com.proj.jwtsec.user.UserInfo;
import com.proj.jwtsec.auth.service.UserStatementService;
import com.proj.jwtsec.entity.SavingsAndExpensesEntity;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserInfoService {
	
	private final UserInfoRepository repo;	
	private final UserStatementService stmtservice;
	private final UserRepository userRepo;
	
	public UserInfo getUserInfo(String email){
		UserInfo user=repo.findByUserEmail(email);
		System.out.println(user);
		return user;
	}

	public String updateSavingsAndBalance(int accno,SavingsAndExpensesEntity entity) {
		
		UserInfo user=repo.findById(accno).get();
		user.setBalance(stmtservice.setStatement(accno,entity));
		repo.save(user);
		
		return "Balance updated successfully";
	}
	
	public int getAccno(String userEmail) {
		UserInfo user=repo.findByUserEmail(userEmail);
		return user.getAccno();  
	}
	
	public UserInfo getUserByAccno(int accno) {
		return repo.findById(accno).get();
	}
	
	public void saveUserInfo(UserInfo user) {
		repo.save(user);
	}
	
	public String deleteUserInfo(String email) {
		repo.deleteById(getAccno(email));
		userRepo.deleteByEmail(email);
		return "User account deleted successfully";
	}

	public List<UserInfo> getAllUserInfo() {
		List<UserInfo> userInfoList=new ArrayList();
		repo.findAll().forEach( user -> userInfoList.add(user));
		return userInfoList;		
	}

}
