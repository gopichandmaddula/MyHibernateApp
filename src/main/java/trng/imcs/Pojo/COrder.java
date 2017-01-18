package trng.imcs.Pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "corder")
public class COrder 
{
	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Integer orderId;
	
@Column(name = "order_description")
private String description;
	
@Column(name = "order_total")
private Integer  orderTotal;

@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//mappedBy attribute is only necessary for a bidirectional relationship, 
private List<Item> items;

public COrder()
{
}
public List<Item> getItems() {
	return items;
}
public void setItems(List<Item> items) {
	this.items = items;
}
@ManyToOne
@JoinColumn(name = "customer_id", nullable=false)
private Customer customer;


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((customer == null) ? 0 : customer.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + orderId;
	result = prime * result + orderTotal;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	COrder other = (COrder) obj;
	if (customer == null) {
		if (other.customer != null)
			return false;
	} else if (!customer.equals(other.customer))
		return false;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	if (orderId != other.orderId)
		return false;
	if (orderTotal != other.orderTotal)
		return false;
	return true;
}
public COrder(String description, Integer orderTotal, Integer orderId) {
	super();
	this.description = description;
	this.orderTotal = orderTotal;
	this.orderId = orderId;
	
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Integer getOrderTotal() {
	return orderTotal;
}
public void setOrderTotal(Integer orderTotal) {
	this.orderTotal = orderTotal;
}
public Integer getOrderId() {
	return orderId;
}
public void setOrderId(Integer orderId) {
	this.orderId = orderId;
}
public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
@Override
public String toString() {
	return "Order [description=" + description + ", orderTotal=" + orderTotal + ", orderId=" + orderId + ", customer="
			+ customer + "]";
}


}
