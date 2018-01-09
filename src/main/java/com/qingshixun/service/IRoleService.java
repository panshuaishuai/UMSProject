package com.qingshixun.service;

import java.util.List;

import com.qingshixun.core.ResponseData;
import com.qingshixun.model.Jurisdiction;
import com.qingshixun.model.Role;

public interface IRoleService {
	
	void saveRole(Role role);
	
	List<Jurisdiction> queryJurisdiction(Jurisdiction jurisdiction, int updateRoleId);
	
	List<Jurisdiction> queryJurisdictions(Jurisdiction jurisdiction);
	
	List<Role> queryRole(Role role);
		
	ResponseData removeRole(int roleId);
	
	Boolean removeRoleJurisdiction(int jurisdictionId);
	
	Role queryUpdateRole(int updateRoleId);
	
	void saveOrUpdateRole(Role role);
	
	void batchRemoveRole(List<Integer> checkedId);
}
