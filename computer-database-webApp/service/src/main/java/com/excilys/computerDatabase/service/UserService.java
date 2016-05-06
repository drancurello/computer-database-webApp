package com.excilys.computerDatabase.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerDatabase.dao.UserDAO;
import com.excilys.computerDatabase.model.UserInfo;

@Component
@Transactional
public class UserService implements UserDetailsService{

	@Autowired
	UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userDAO.getUserInfo(username);
		System.out.println(user.toString());
		List<GrantedAuthority> authorities = buildUserAuthority(user);
		return buildUserForAuthentication(user, authorities);
	}
	
	public UserInfo insertUserInfo(UserInfo user) {
		user = userDAO.addUserInfo(user);
		
		return user;
	}
	
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(
            UserInfo user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(UserInfo user) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(user.getRole()));
        return grantedAuthorityList;
    }
	
	
}
