package com.qingshixun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingshixun.core.ResponseData;
import com.qingshixun.dao.IDepartmentDao;
import com.qingshixun.service.IDepartmentService;
import com.qingshixun.service.IUserService;
import com.qingshixun.model.Department;

@Service("departmentService")
@Transactional
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentDao departmentDao;
	
	@Autowired
	private IUserService userService;

	/**
	 * 查询部门信息
	 */
	@Override
	public List<Department> queryDepartment(Department department) {
		List<Department> list = departmentDao.queryDepartment(department);
		return list;
	}

	/**
	 * 添加部门信息
	 */
	@Override
	public void saveDepartment(Department department) {
		departmentDao.saveDepartment(department);
	}

	/**
	 * 通过参数departmentId查询并删除对应部门信息
	 */
	@Override
	public ResponseData deleteDepartment(int departmentId) {
		ResponseData responseData = new ResponseData();
		if (!userService.removeUserDepartment(departmentId)) {
			responseData.setError("部门已被使用，无法删除！");
			return responseData;
		}
		departmentDao.deleteDepartment(departmentId);
		responseData.setError("删除成功");
		return responseData;
	}

	/**
	 * 通过参数checkedId查询并删除对应id集合的部门信息
	 */
	@Override
	public void batchDeleteDepartment(List<Integer> checkedId) {
		departmentDao.batchDeleteDepartment(checkedId);
	}

	/**
	 * 通过参数updateId查询并修改对应部门信息
	 */
	@Override
	public Department querUpDepartment(int updateId) {
		Department departmentInfo = departmentDao.querUpDepartment(updateId);
		return departmentInfo;
	}

	/**
	 * 保存已修改的部门信息
	 */
	@Override
	public void editDepartment(Department department) {
		departmentDao.eidtDepartment(department);
	}
}
