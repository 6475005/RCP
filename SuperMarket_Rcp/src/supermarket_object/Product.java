package supermarket_object;

import java.util.HashSet;
import java.util.Set;
public class Product {
	private int Pid;
	private String name;
	private float price;
	private int margin;
	private boolean state = true;
	private Type type;
	private Set<SalesRecord> salesrecord = new HashSet<SalesRecord>();
	
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getPid() {
		return Pid;
	}

	public Set<SalesRecord> getSalesrecord() {
		return salesrecord;
	}

	public void setSalesrecord(Set<SalesRecord> salesrecord) {
		this.salesrecord = salesrecord;
	}

	public void setPid(int pid) {
		Pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getMargin() {
		return margin;
	}
	public void setMargin(int margin) {
		this.margin = margin;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
}
