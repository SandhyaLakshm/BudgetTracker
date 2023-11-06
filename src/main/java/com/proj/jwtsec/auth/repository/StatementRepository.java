package com.proj.jwtsec.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proj.jwtsec.user.Statement;

@Repository
public interface StatementRepository extends CrudRepository<Statement, Integer> {

	boolean existsByAccno(int accno);
}
