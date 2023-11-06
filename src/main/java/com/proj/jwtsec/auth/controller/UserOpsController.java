package com.proj.jwtsec.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.jwtsec.auth.AuthenticationService;
import com.proj.jwtsec.auth.repository.UserInfoRepository;
import com.proj.jwtsec.auth.service.UserInfoService;
import com.proj.jwtsec.config.JwtService;
import com.proj.jwtsec.entity.SavingsAndExpensesEntity;
import com.proj.jwtsec.user.UserInfo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bank")//this mapping requires authentication
public class UserOpsController {
	
	private final JwtService jwtService;
	private final HttpServletRequest request;
	private final UserInfoService userInfoService;
	

	public String getUserEmail(HttpServletRequest request) {
		final String jwt=request.getHeader("Authorization").substring(7);
		String userEmail=jwtService.extractUsername(jwt);	
		return userEmail;
	}

	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return ResponseEntity.ok("Hello from secured endpoint "+getUserEmail(request));
	}
	
	@GetMapping("/userInfo")
	public ResponseEntity<UserInfo> userInfo(){
		UserInfo userInfo=userInfoService.getUserInfo(getUserEmail(request));
		return ResponseEntity.ok(userInfo);
	}
	
	@PostMapping("/user/savings")
	public ResponseEntity<String> updateSavingsAndBalance(@RequestBody SavingsAndExpensesEntity entity){
		int accno=userInfoService.getAccno(getUserEmail(request));
		return ResponseEntity.ok(userInfoService.updateSavingsAndBalance(accno,entity));
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(){
		return ResponseEntity.ok(userInfoService.deleteUserInfo(
				getUserEmail(request)));
	}
	
//	@GetMapping("/allUserInfo")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public ResponseEntity<List<UserInfo>> getAllUsers(){
//		List<UserInfo> usersList=userInfoService.getAllUserInfo();
//		return ResponseEntity.ok(usersList);
//	}

}
