/********************************************
 * Copyright (c) 2016, www.qingshixun.com
 *
 * All rights reserved
 *
*********************************************/
package com.qingshixun.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qingshixun.dao.IUserDao;
import com.qingshixun.model.Department;
import com.qingshixun.model.Role;
import com.qingshixun.model.User;

@Repository("userDao")
public class UserDao implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 通过用户名查询用户信息，判断用户是否存在
	 */
	@Override
	public User queryUserLogin(String username) {
		int index = 0;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where username = ?");
		query.setParameter(0, username);
		List<User> list = query.list();
		if (list.size() != 0) {
			return list.get(index);
		}
		return null;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 保存用户信息
	 */
	@Override
	public boolean saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		try {
			user.setCreateDate(new Date());
			session.save(user);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	/**
	 * 通过参数userid查询对应用户信息，并删除用户
	 */
	@Override
	public void removeUser(int userid) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user = (User) session.load(User.class, userid);
		session.delete(user);
	}
	
	/**
	 * 通过参数roleId查询对应用户的角色关联
	 */
	@Override
	public Integer removeUserRole(int roleId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where role.id = ?");
		query.setParameter(0, roleId);
		return query.list().size();
	}
	
	/**
	 * 通过参数departmentId查询对应用户的部门关联
	 */
	@Override
	public Integer removeUserDepartment(int departmentId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where department.id = ?");
		query.setParameter(0, departmentId);
		return query.list().size();
	}
	
	/**
	 * 通过id集合checkedId参数，查询权限信息，实现多项删除
	 */
	@Override
	public void batchDeleteUser(List<Integer> checkedId) {
		Session session = sessionFactory.getCurrentSession();
		for (int i = 0; i < checkedId.size(); i++) {
			User user = new User();
			int id = checkedId.get(i);
			user = (User) session.get(User.class, id);
			session.delete(user);
		}
	}

	/**
	 * 通过参数updateid查询对应的用户信息
	 */
	@Override
	public User queryUpdateUser(int updateid) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user = (User) session.load(User.class, updateid);
		System.out.println(user);
		return user;
	}

	/**
	 * 修改并保存用户信息
	 */
	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	/**
	 * 查询用户信息，实现分页
	 */
	@Override
	public List<User> queryAllUser(int pageNow, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User order by id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNow - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<User> list = query.list();
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 查询用户信息数量
	 */
	@Override
	public int userSize() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User";
		int sezi = session.createQuery(hql).list().size();
		if (sezi <= 0) {
			return 0;
		}
		return sezi;
	}

	/**
	 * 通过用户名查询用户信息
	 */
	@Override
	public int usernameVerification(String name) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE username =?";
		int flag = 0;
		try {
			Query query = session.createQuery(hql);
			query.setString(0, name);
			List<User> list = query.list();
			if (list.size() > 0) {
				flag = 1;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;
	}

	/**
	 * 查询角色信息
	 */
	@Override
	public List<Role> queryRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Role> listRole = session.createQuery("FROM Role").list();
		return listRole;
	}

	/**
	 * 查询部门信息
	 */
	@Override
	public List<Department> queryDepartment(Department department) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Department> list = session.createQuery("FROM Department").list();
		return list;
	}

	/**
	 * 模糊查询用户信息
	 * @param name
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<User> queryLikeUser(String name, int pageNow, int pageSize) {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM User as user WHERE user.name like :name");
		query.setParameter("name", "%"+name+"%");
		query.setFirstResult((pageNow - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<User> list = query.list();
//		if (list.size() > 0) {
//			return list;
//		}
		return list;
  }
	
	/**
	 * 模糊查询的用户信息数量
	 */
	@Override
	public int userLikeSize(String name) {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM User as user WHERE user.name like :name");
		query.setParameter("name", "%"+name+"%");
		int size = query.list().size();
		if (size <= 0) {
			return 0;
		}
		return size;
	}
}
