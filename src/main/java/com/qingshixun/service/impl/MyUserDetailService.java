package com.qingshixun.service.impl;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qingshixun.dao.impl.BaseDaoImpl;
import com.qingshixun.model.User;
import com.qingshixun.service.IUserService;

@Component
@Transactional
public class MyUserDetailService extends BaseDaoImpl<User> implements UserDetailsService {

	@Autowired
	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// 可以返回User类型之后在转换成UserDetails类型ps:实体bean必须实现UserDetails接口，否则无法转换
		UserDetails user = findByUsername(username);
		User user1 = userService.queryUserLogin(username);

		// 用户不存在
        if (user1 == null) {
            throw new BadCredentialsException("用户名或密码不正确！");
        }

        // 用户被禁用
        if (user1.getStatus().equals("禁用")) {
            throw new BadCredentialsException("此用户已被禁用！");
        }

        return user = (UserDetails)user1;
	}
}
