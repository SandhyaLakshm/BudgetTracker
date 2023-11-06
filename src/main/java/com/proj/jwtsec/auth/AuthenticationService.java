package com.proj.jwtsec.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proj.jwtsec.auth.repository.UserRepository;
import com.proj.jwtsec.auth.service.UserInfoService;
import com.proj.jwtsec.config.JwtService;
import com.proj.jwtsec.user.Role;
import com.proj.jwtsec.user.User;
import com.proj.jwtsec.user.UserInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final UserInfoService userInfoService;

	public AuthenticationResponse register(RegisterRequest request) {
		var user=User.builder().firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
		repository.save(user);
		UserInfo userInfo=new UserInfo(request.getFirstname(),request.getLastname(),request.getEmail());
		userInfoService.saveUserInfo(userInfo);
		var jwtToken=jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user=repository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
		var jwtToken=jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

}
