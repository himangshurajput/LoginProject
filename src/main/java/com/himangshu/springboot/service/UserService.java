package com.himangshu.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.himangshu.springboot.model.User;
import com.himangshu.springboot.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
