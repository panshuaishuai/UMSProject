package com.qingshixun.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingshixun.core.ResponseData;
import com.qingshixun.dao.IJurisdictionDao;
import com.qingshixun.service.IJurisdictionService;
import com.qingshixun.service.IRoleService;
import com.qingshixun.model.Jurisdiction;
import com.qingshixun.model.Role;

@Service("jurisdictionService")
@Transactional
public class JurisdictionService implements IJurisdictionService {

	@Autowired
	private IJurisdictionDao jurisdictionDao;
	
	@Autowired
	private IRoleService roleService;

	/**
	 * 添加权限信息
	 */
	@Override
	public void saveJurisdiction(Jurisdiction jurisdiction) {
		jurisdictionDao.saveJurisdiction(jurisdiction);

	}

	/**
	 * 查询所有权限信息
	 */
	@Override
	public List<Jurisdiction> queryjurisdiction(Jurisdiction jurisdiction) {
		List<Jurisdiction> list = jurisdictionDao.queryjurisdiction(jurisdiction);
		return list;
	}

	/**
	 * 通过参数jurisdictionId查询并删除对应的权限信息
	 */
	@Override
	public ResponseData removeJurisdiction(int jurisdictionId) {
		ResponseData responseData = new ResponseData();
		if (!roleService.removeRoleJurisdiction(jurisdictionId)) {
			responseData.setError("权限已被使用，无法删除！");
			return responseData;
		}
		jurisdictionDao.removeJurisdiction(jurisdictionId);
		responseData.setError("删除成功");
		return responseData;
	}
	
	/**
	 * 通过参数updateId查询对应权限信息
	 */
	@Override
	public Jurisdiction queryJurisdictions(int updateId) {
		return jurisdictionDao.queryJurisdictions(updateId);
	}

	/**
	 * 修改并保存权限信息
	 */
	@Override
	public void updateJurisdiction(Jurisdiction jurisdiction) {
		jurisdictionDao.updateJurisdiction(jurisdiction);

	}

	/**
	 * 通过checkedId集合查询并删除对应的权限集合信息
	 */
	@Override
	public void BatchRemoveJurisdiction(List<Integer> checkedId) {
		jurisdictionDao.BatchRemoveJurisdiction(checkedId);

	}

}
