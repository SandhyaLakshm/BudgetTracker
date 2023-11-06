package com.proj.jwtsec.auth.exception;

public class DuplicateEmailException extends RuntimeException{

	public DuplicateEmailException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DuplicateEmailException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DuplicateEmailException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
