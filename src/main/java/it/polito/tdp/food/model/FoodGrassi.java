package it.polito.tdp.food.model;

public class FoodGrassi implements Comparable<FoodGrassi> {

	private int id;
	private String name;
	private double grassi;
	public FoodGrassi(int id, String name, double grassi) {
		super();
		this.id = id;
		this.name = name;
		this.grassi = grassi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getGrassi() {
		return grassi;
	}
	public void setGrassi(double grassi) {
		this.grassi = grassi;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(grassi);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		FoodGrassi other = (FoodGrassi) obj;
		if (Double.doubleToLongBits(grassi) != Double.doubleToLongBits(other.grassi))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return name + ", grassi=" + grassi + "\n";
	}
	@Override
	public int compareTo(FoodGrassi o) {
		// TODO Auto-generated method stub
		return (int)o.grassi-(int)this.grassi;
	}
	
	
	
	
}
