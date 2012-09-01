package supermarket_object;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SalesRecord {
	private int Sid;
	private Date date;
	private Set<Product> products_sr = new HashSet<Product>();
	private Map<String,Integer> sr_map = new HashMap<String, Integer>();
	public int getSid() {
		return Sid;
	}
	public void setSid(int sid) {
		Sid = sid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Set<Product> getProducts_sr() {
		return products_sr;
	}
	public void setProducts_sr(Set<Product> products_sr) {
		this.products_sr = products_sr;
	}
	public Map<String, Integer> getSr_map() {
		return sr_map;
	}
	public void setSr_map(Map<String, Integer> sr_map) {
		this.sr_map = sr_map;
	}
	
	
	
	
}
