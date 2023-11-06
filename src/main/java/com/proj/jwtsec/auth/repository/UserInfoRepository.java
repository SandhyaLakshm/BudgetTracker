package com.proj.jwtsec.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proj.jwtsec.user.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {

	List<UserInfo> findAllByUserEmail(String email);
	
	UserInfo findByUserEmail(String email);
	
}
