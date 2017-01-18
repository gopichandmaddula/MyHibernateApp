package trng.Util;

import java.util.List;

import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import com.mysql.jdbc.log.Log4JLogger;


import trng.imcs.Pojo.Item;

public class HibernateUtils 
{

	private static final SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;
    
    private static SessionFactory buildSessionFactory() {
		try {
	            Configuration configuration = new Configuration();
	            configuration.configure("hibernate.cfg.xml");
	            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
	            return sessionFactory;
		} catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
  
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


	public static List<Item> getItemsByCriteria(String firstName, String description, Integer itemId)
	{
		// TODO Auto-generated method stub
		//logger.log(Level.INFO, "getItemsByCriteria() start with "+firstName+", "+description+", "+itemId);
		Session session = sessionFactory.openSession();

		Criteria itemCriteria = session.createCriteria(Item.class);
		Criteria orderCriteria = itemCriteria.createCriteria("order", "o");
		Criteria customerCriteria = orderCriteria.createCriteria("customer", "c");
		
		if(description!=null) {
			orderCriteria.add(Restrictions.like("description", "%" + description + "%"));
		}

		if (firstName != null) {
			customerCriteria.add(Restrictions.like("firstName", firstName));
		}
		
		if(itemId!=null) {
			itemCriteria.add(Restrictions.eq("itemId", itemId));
		}
		
		return (List<Item>)orderCriteria.list();
	}
}