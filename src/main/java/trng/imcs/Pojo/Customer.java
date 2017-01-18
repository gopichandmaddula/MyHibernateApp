package trng.imcs.Pojo;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table(name = "customer")
public class Customer 
{
	@Id
	@GeneratedValue
	private Integer customerId;
	@Column(name = "first_name", nullable=false)
	private String firstName;
	@Column(name = "last_name", nullable=false)
	private String lastName;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	//mappedBy attribute is only necessary for a bidirectional relationship, 
	private List<COrder> orderDetails;
	
	
	public Customer(Integer customerId, String firstName, String lastName) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	public Customer()
	{
		
	}
	public List<COrder> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<COrder> orderDetails) {
		this.orderDetails = orderDetails;
	}
	@Override
	public int hashCode() {
		final Integer prime = 31;
		Integer result = 1;
		result = prime * result + customerId;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		//result = prime * result + ((orderDetails == null) ? 0 : orderDetails.hashCode());
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
		Customer other = (Customer) obj;
		if (customerId != other.customerId)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (orderDetails == null) {
			if (other.orderDetails != null)
				return false;
		} else if (!orderDetails.equals(other.orderDetails))
			return false;
		return true;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ",]";
	}
	
	

}
