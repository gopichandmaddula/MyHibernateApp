package trng.imcs.Pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "item")
@NamedQueries ({
	@NamedQuery(name="findItemsOfCustomer", 
						query="SELECT i FROM Item i INNER JOIN i.order o INNER JOIN o.customer c "
								+ "WHERE c.customerId = :customerId")})
public class Item {
	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Integer itemId;
	@Column(name = "item_price")
	private Integer price;
	@Column(name = "item_description")
	private String description;
	@Column(name = "item_quantity")
	private Integer quantity;
	@ManyToOne
	@JoinColumn(name = "order_id", nullable=false)
	private COrder order;
	
	public Item()
	{}
	
	
	public Item(Integer itemId, Integer price, String description, Integer quantity) {
		super();
		this.itemId = itemId;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + itemId;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + price;
		result = prime * result + quantity;
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
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (itemId != other.itemId)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (price != other.price)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	public COrder getOrder() {
		return order;
	}
	public void setOrder(COrder order) {
		this.order = order;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", price=" + price + ", description=" + description + ", quantity=" + quantity
				+ ", order=" + order + "]";
	}
	
	

}
