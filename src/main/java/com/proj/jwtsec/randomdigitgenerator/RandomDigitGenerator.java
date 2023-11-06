package com.proj.jwtsec.randomdigitgenerator;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor; 
import org.hibernate.id.IdentifierGenerator;

public class RandomDigitGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		
		Random random=new Random();
		int num=random.nextInt(90000)+10000;
		return (Serializable)num;
		
	}

}
