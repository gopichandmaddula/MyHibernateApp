package trng.Util;

import java.util.ArrayList;
import java.util.List;

import trng.imcs.Pojo.Item;
import trng.imcs.Pojo.COrder;
import trng.imcs.Pojo.*;

public class ADDCustomer {
	
	public static Customer addCustomer(Customer customer)
	{
		COrder firstorder = new COrder("Firstorder", 100, null);
        Item firstitem = new Item(null,50,"firstitem",3);
        Item seconditem = new Item(null,14,"seconditem",2);
        List<Item> itemdetails = new ArrayList<>();
        itemdetails.add(firstitem);
        itemdetails.add(seconditem);
        firstorder.setItems(itemdetails);
       firstitem.setOrder(firstorder);
       seconditem.setOrder(firstorder);
        COrder secondorder = new COrder("Secondorder", 75, null);
        Item s_firstitem = new Item(null,50,"firstitem",3);
        Item s_seconditem = new Item(null,14,"seconditem",2);
        List<Item> s_itemdetails = new ArrayList<>();
        s_itemdetails.add(s_firstitem);
        s_itemdetails.add(s_seconditem);
        secondorder.setItems(s_itemdetails);
        s_firstitem.setOrder(secondorder);
        s_seconditem.setOrder(secondorder);
        COrder thirdorder = new COrder("Thirdorder", 150,null);
        Item t_firstitem = new Item(null,50,"firstitem",3);
        Item t_seconditem = new Item(null,14,"seconditem",2);
        List<Item> t_itemdetails = new ArrayList<>();
        t_itemdetails.add(t_firstitem);
        t_itemdetails.add(t_seconditem);
        thirdorder.setItems(t_itemdetails);
        t_firstitem.setOrder(thirdorder);
        t_seconditem.setOrder(thirdorder);
        
        
        firstorder.setCustomer(customer);
        secondorder.setCustomer(customer);
        thirdorder.setCustomer(customer);
        List<COrder> orderDetails = new ArrayList<>();
        orderDetails.add(firstorder);
        orderDetails.add(secondorder);
        orderDetails.add(thirdorder);
       customer.setOrderDetails(orderDetails);
       return customer;
       
	}

}
