package trng.Dao;

import trng.imcs.Pojo.*;


public interface ICustomerDao {
	public void addCustomer(Customer customer);
	public Customer loadCustomer(Integer customerId);

}
