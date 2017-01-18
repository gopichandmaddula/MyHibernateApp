package trng.Dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;




import trng.Util.*;
import trng.imcs.Pojo.*;

public class CustomerDao implements ICustomerDao
{
final static Logger logger = Logger.getLogger(CustomerDao.class);
	
SessionFactory sf;	
public CustomerDao() 
	{
		sf = HibernateUtils.getSessionFactory();
	}

public void addCustomer(Customer customer) {
	logger.debug("Executing StudentDao::addStudent API" + customer.getCustomerId());
	sf = HibernateUtils.getSessionFactory();
    Session session = sf.openSession();
    
    Transaction transaction = session.beginTransaction();
    
    try {
		session.save(customer);
	} catch (Exception e) {
		logger.error("failed to execute addStudent method", e);
	}
	transaction.commit();
    session.close();
    logger.debug("Completed executing StudentDao::addStudent API");
}

public Customer loadCustomer(Integer customerId){
	sf = HibernateUtils.getSessionFactory();
    Session session = sf.openSession();
    
    session.beginTransaction();
    
    Customer customer = (Customer) session.get(Customer.class,customerId);
    
    session.getTransaction().commit();
    session.close();
    
    return customer;
}
	

}
