package com.qingshixun.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingshixun.core.ResponseData;
import com.qingshixun.dao.IRoleDao;
import com.qingshixun.service.IRoleService;
import com.qingshixun.service.IUserService;
import com.qingshixun.model.Jurisdiction;
import com.qingshixun.model.Role;

@Transactional
@Service("roleService")
public class RoleService implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private IUserService userService;

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/*
	 * 添加角色信息
	 */
	@Override
	public void saveRole(Role role) {
		roleDao.saveRole(role);
	}

	/*
	 * 查询所有权限信息中要修改的信息
	 */
	@Override
	public List<Jurisdiction> queryJurisdiction(Jurisdiction jurisdiction, int updateRoleId) {
		Role queryRole = roleDao.queryUpdateRole(updateRoleId);
		List<Jurisdiction> queryJurisdiction = roleDao.queryJurisdiction(jurisdiction);
		for (Jurisdiction jurisdiction1 : queryJurisdiction) {
				Set<Jurisdiction> jurisdictions = queryRole.getJurisdictions();
				for (Jurisdiction jurisdiction2 : jurisdictions) {
					if (jurisdiction1.getId() == jurisdiction2.getId()) {
						System.out.println(jurisdiction2.getId());
						jurisdiction1.setRoleJurisdiction(true);
						break;
					}
			}
			
		}
		return queryJurisdiction;
	}
	
	/*
	 * 查询所有权限信息
	 */
	@Override
	public List<Jurisdiction> queryJurisdictions(Jurisdiction jurisdiction) {
		return roleDao.queryJurisdiction(jurisdiction);
	}

	/*
	 * 查询所有角色信息
	 */
	@Override
	public List<Role> queryRole(Role role) {
		return roleDao.queryRole(role);
	}

	/*
	 * 通过参数roleId查询并删除对应的角色信息
	 */
	@Override
	public ResponseData removeRole(int roleId) {
		ResponseData responseData = new ResponseData();
		if (!userService.removeUserRole(roleId)) {
			responseData.setError("角色已被使用，无法删除！");
			return responseData;
		}
		
		roleDao.removeRole(roleId);
		responseData.setError("删除成功");
		return responseData;
	}
	
	/**
	 * 验证权限是否被角色关联
	 * @param jurisdictionId
	 * @return true:未使用 false:已使用
	 */
	@Override
	public Boolean removeRoleJurisdiction(int jurisdictionId) {
		return roleDao.removeRoleJurisdiction(jurisdictionId) == 0;
	}

	/*
	 * 通过参数updateRoleId查询对应的角色信息
	 */
	@Override
	public Role queryUpdateRole(int updateRoleId) {
		return roleDao.queryUpdateRole(updateRoleId);
	}

	/*
	 * 修改并保存角色信息
	 */
	@Override
	public void saveOrUpdateRole(Role role) {
		roleDao.saveOrUpdateRole(role);
	}

	/*
	 * 通过checkedId集合查询并删除对应集合的角色信息
	 */
	@Override
	public void batchRemoveRole(List<Integer> checkedId) {
		roleDao.batchRemoveRole(checkedId);
	}
}
