package trng.hbrnt;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import trng.imcs.Pojo.*;
import trng.Dao.*;
import trng.Util.ADDCustomer;
import trng.Util.HibernateUtils;


public class CustomerApp 
{
	final static Logger logger = Logger.getLogger(CustomerApp.class);

	ICustomerDao customerDao;
	static Scanner scanner;
	
	public CustomerApp() {
		customerDao = new CustomerDao();
		scanner = new Scanner(System.in);
	}
	
    public static void main( String[] args )
    {
    	CustomerApp customerApp = new CustomerApp();
    	
    	System.out.println("Choose the operation");
    	boolean flag = true;
    	
    	do {
        	System.out.println("1 Add \n 2. Add Customer with Details \n "
        			+ "3. Select Customer by Order Total \n 4. DISPLAY ALL ITEMS OF CUSTOMER \n 5.Criteria");
        	StudentOperation opVal = StudentOperation.getValue(scanner.nextInt());

	    	switch(opVal) {
	    		case ADD_STUDENT:
	    			customerApp.addStudent();
	    			break;
	    			
	    		case ADD_CUSTOMER_WITH_DETAILS:
	    			customerApp.addStudentWithDetail(); 
	    			break;
	
	    		case SELECT_CUSTOMER_BY_ORDER_TOTAL:
	    		 customerApp.selectCustomer(100); 
	    			break;

	    		case DISPLAY_ITEMS:
	    			customerApp.displayItems(); 
	    			break;
	    		case CRITERIA:
	    			customerApp.criteria(); 
	    			break;

	    		default: flag = false;
	    	}
    	} while (flag);
    	
    	scanner.close();
    }
    private void criteria() {
		// TODO Auto-generated method stub
    	System.out.println("Enter firstName : ");
		String firstName = scanner.next();
		System.out.println("Enter Item description : ");
		String description = scanner.next();
		System.out.println("enter item id : ");
		Integer itemId = scanner.nextInt();

		List<Item> orders = HibernateUtils.getItemsByCriteria(firstName, description, itemId);
		
		for(Item i : orders)
		{
			System.out.println(i);
		}
		
	}

	private void displayItems() 
    {
		// TODO Auto-generated method stub
    	System.out.println("\nEnter customer Id : ");
		int custId = scanner.nextInt();
		SessionFactory sf;
		sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Customer customer = (Customer)session.get(Customer.class, custId);
        Query query = session.getNamedQuery("findItemsOfCustomer");
		query.setParameter("customerId", customer.getCustomerId());
		List<Item> items = query.list();
		session.getTransaction().commit();
        session.close();
	   for(Item i : items)	
	   {
		   System.out.println(i);
	   }
   }

	private void selectCustomer(Integer order_total) 
	{
		// TODO Auto-generated method stub
		SessionFactory sf;
		sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
Query query =  session.createQuery("SELECT c FROM COrder o INNER JOIN o.customer c WHERE o.orderTotal > :order_total");
        query.setParameter("order_total", order_total);
        List<Customer> customerLst = query.list();
        session.getTransaction().commit();
        session.close();
        for(Customer d : customerLst)
        {
        	System.out.println(d);
        }
        }

	private void addStudent()  {
        Customer customer = new Customer(1,"Gopi", "Chand");

        customerDao.addCustomer(customer);
    }

	
	private void addStudentWithDetail() 
	{
        Customer customer = new Customer(null, "Gopi", "Chand");
        Customer customer1 = ADDCustomer.addCustomer(customer);
        customerDao.addCustomer(customer1);
        Customer customer2 = new Customer(null, "vishnu", "Reddy");
        Customer temp = ADDCustomer.addCustomer(customer2);
        customerDao.addCustomer(temp);
        
    }
    

  
    
    enum StudentOperation {
		ADD_STUDENT(1),  ADD_CUSTOMER_WITH_DETAILS(2),  SELECT_CUSTOMER_BY_ORDER_TOTAL (3), DISPLAY_ITEMS (4), CRITERIA(5);
		
    	int operationVal;
		
		StudentOperation(int opVal) {
			operationVal = opVal;
		}
		
		static StudentOperation getValue(int opVal) {
			for (StudentOperation studentOperation : StudentOperation.values()) {
				if (opVal == studentOperation.operationVal) {
					return studentOperation;
				}
			}
			
			return null;
		}
	
    } }

