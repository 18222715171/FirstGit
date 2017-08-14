package com.itheima.crm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.utils.MD5Utils;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao dao;
	
	public List<Customer> findAll() {
		return dao.findAll();
	}

	/**
	 * 查询未关联到定区的客户数据
	 */
	public List<Customer> findCustomerNotAssociation() {
		return dao.findByFixedAreaIdIsNull();
	}

	/**
	 * 查询已经关联到指定定区的客户数据
	 */
	public List<Customer> findCustomerHasAssociation(String fixedAreaId) {
		return dao.findByFixedAreaId(fixedAreaId);
	}

	/**
	 * 将定区和客户进行关联
	 */
	public void assignCustomers2FixedArea(String fixedAreaId, Integer[] customerIds) {
		//清理关系
		dao.emptyFixedArea(fixedAreaId);
		if(customerIds != null && customerIds.length > 0){
			for (Integer customerId : customerIds) {
				dao.assignCustomers2FixedArea(fixedAreaId, customerId);
			}
		}
	}

	/**
	 * 客户注册
	 */
	public void regist(Customer customer) {
		String md5 = MD5Utils.md5(customer.getPassword());
		customer.setPassword(md5);
		dao.save(customer);
	}

	/**
	 * 根据客户手机号查询客户信息
	 */
	public Customer findCustomerByTelephone(String telephone) {
		return dao.findByTelephone(telephone);
	}

	/**
	 * 激活邮件
	 */
	public void activeEmail(String telephone) {
		dao.activeEmail(telephone);
	}
	
	/**
	 * 客户登录方法
	 */
	public Customer login(String telephone, String password) {
		password = MD5Utils.md5(password);
		return dao.findByTelephoneAndPassword(telephone,password);
	}

	/**
	 * 根据客户的住址信息查询定区id
	 */
	public String findFixedAreaIdByAddress(String address) {
		return dao.findFixedAreaIdByAddress(address);
	}

	/**
	 * 根据定区ID查询客户
	 */
	@Override
	public List<Customer> findCustomerByFixedId(String fixedId) {
		return dao.findCustomerByFixedId(fixedId);
	}
}
