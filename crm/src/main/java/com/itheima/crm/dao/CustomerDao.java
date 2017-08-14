package com.itheima.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.crm.domain.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
	/**
	 * 查询未关联到定区的客户数据
	 */
	public List<Customer> findByFixedAreaIdIsNull();
	
	/**
	 * 查询已经关联到指定定区的客户数据
	 */
	public List<Customer> findByFixedAreaId(String fixedAreaId);
	
	/**
	 * 定区和客户解除关联关系
	 */
	@Query("update Customer set fixedAreaId = null where fixedAreaId = ?")
	@Modifying
	public void emptyFixedArea(String fixedAreaId);
	
	/**
	 * 定区和客户建立关联关系
	 */
	@Query("update Customer set fixedAreaId = ? where id = ?")
	@Modifying
	public void assignCustomers2FixedArea(String fixedAreaId,Integer customerId);

	/**
	 * 根据手机号查询客户信息
	 * @param telephone
	 * @return
	 */
	public Customer findByTelephone(String telephone);

	/**
	 * 激活邮件
	 * @param telephone
	 */
	@Query("update Customer set type = 1 where telephone = ?")
	@Modifying
	public void activeEmail(String telephone);

	/**
	 * 根据手机号和密码查询客户
	 * @param telephone
	 * @param password
	 * @return
	 */
	public Customer findByTelephoneAndPassword(String telephone, String password);

	/**
	 * 根据客户住址查询定区id
	 * @param address
	 * @return
	 */
	@Query("select fixedAreaId from Customer where address = ?")
	public String findFixedAreaIdByAddress(String address);

	/**
	 * 根据定区id查询客户
	 * @param address
	 * @return
	 */
	@Query("from Customer where fixedAreaId = ?")
	public List<Customer> findCustomerByFixedId(String fixedId);
}
