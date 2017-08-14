package com.itheima.crm.service;

import java.util.List;
import javax.jws.WebService;
import com.itheima.crm.domain.Customer;

@WebService
public interface CustomerService {
	/**
	 * 查询CRM数据库中所有的客户信息
	 * @return
	 */
	public List<Customer> findAll();
	
	/**
	 * 查询未关联到定区的客户数据
	 */
	public List<Customer> findCustomerNotAssociation();
	
	/**
	 * 查询已经关联到指定定区的客户数据
	 */
	public List<Customer> findCustomerHasAssociation(String fixedAreaId);
	
	/**
	 * 将定区和客户进行关联
	 */
	public void assignCustomers2FixedArea(String fixedAreaId,Integer[] customerIds);
	
	/**
	 * 客户注册
	 */
	public void regist(Customer customer);
	
	/**
	 * 根据客户手机号查询客户信息
	 */
	public Customer findCustomerByTelephone(String telephone);
	
	/**
	 * 激活邮件
	 */
	public void activeEmail(String telephone);
	
	/**
	 * 客户登录
	 */
	public Customer login(String telephone,String password);
	
	/**
	 * 根据客户的住址信息查询定区id
	 */
	public String findFixedAreaIdByAddress(String address);
	
	public List<Customer> findCustomerByFixedId(String fixedId);
}
