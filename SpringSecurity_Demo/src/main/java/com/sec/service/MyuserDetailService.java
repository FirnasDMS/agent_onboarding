package com.sec.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sec.model.MyUserDetails;
import com.sec.model.User;
import com.sec.repo.UserRepo;
@Service
public class MyuserDetailService implements UserDetailsService{

	@Autowired
	UserRepo UserRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user=UserRepo.findByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException(username+" "+ "Not Found!");
		}
		/*
		try {
			user.orElseThrow(()->new UserPrincipalNotFoundException(username+" "+"Not Found!"));
		} catch (UserPrincipalNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return user.map(MyUserDetails::new).get();
	}

}
